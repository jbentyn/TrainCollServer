package com.bentyn.traincoll.communication;

import java.io.IOException;
import java.util.Set;

import javax.annotation.Resource;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import co.paralleluniverse.spacebase.AABB;

import com.bentyn.traincoll.commons.communication.Message;
import com.bentyn.traincoll.commons.data.EventData;
import com.bentyn.traincoll.commons.data.TrainData;
import com.bentyn.traincoll.controller.MessageController;
import com.bentyn.traincoll.controller.TrainBaseController;
import com.bentyn.traincoll.model.SpatialTrainData;
import com.google.gson.Gson;

@ServerEndpoint(value="/collision", configurator = SpringConfigurator.class)
public class CollisionWebSocket {

	private final static Logger LOG = LoggerFactory.getLogger(CollisionWebSocket.class); 
	
	@Autowired
	private MessageController messageController;
	@Autowired
	private TrainBaseController baseControlller;
	@Autowired
	private Gson gson;
	@Resource(name="websocketSessions")
	private Set<Session> sessions;
	
	
	public CollisionWebSocket() {
		super();
		LOG.debug("WebSocket Endpoint "+ this+" Created");
	}
	@OnOpen
	public void onOpen(Session session, EndpointConfig config){
		LOG.debug("ConnectionOpen");
		sessions.add(session);
		LOG.debug("SessionCount: "+sessions.size());
	}
	@OnMessage
	public void onTextMessage(Session session,String msg, boolean last) throws IOException{
		Message message =  gson.fromJson(msg, Message.class);
		LOG.debug("Message recived: "+message);
		switch(message.getType()){
		case POSITION_UPDATE:
			
			SpatialTrainData train = gson.fromJson(message.getData(), SpatialTrainData.class);
			baseControlller.insertOrUpdate(train,session);
			baseControlller.updatePosition(train, TrainBaseController.COLLISION_CHECKING_RANGE);
			LOG.info("Train "+train.getId()+" position update was sent");
			break;
		case EVENT:
			EventData event =  gson.fromJson(message.getData(),EventData.class);
			LOG.info("Event received"+event);
			messageController.sendToAll(event, sessions);
			break;
		default:
			break;
		}
	}
	@OnClose
	public void onClose(Session session, CloseReason closeReson){
		baseControlller.remove(session);
		sessions.remove(session);
		LOG.info("Session removed, id:"+session.getId()+ "Reason : "+closeReson);
	}
}
