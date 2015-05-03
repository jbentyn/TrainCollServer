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

import com.bentyn.traincoll.commons.communication.Message;
import com.bentyn.traincoll.commons.data.EventData;
import com.bentyn.traincoll.commons.data.TrainData;
import com.bentyn.traincoll.controller.TrainController;
import com.google.gson.Gson;

@ServerEndpoint(value="/collision", configurator = SpringConfigurator.class)
public class CollisionWebSocket {

	private final static Logger LOG = LoggerFactory.getLogger(CollisionWebSocket.class); 
	
	
	@Autowired
	private TrainController trainController;
	
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
		
		switch(message.getType()){
		case POSITION_UPDATE:
			TrainData train = gson.fromJson(message.getData(), TrainData.class);
			trainController.updateTrainPosition(train);
			//TODO send posiotion update
			LOG.info("Position Updated "+train.getId());
			break;
		case EVENT:
			EventData event =  gson.fromJson(message.getData(),EventData.class);
			LOG.info("Event received"+event);
			trainController.sendToAll(event, sessions);
			break;
		default:
			break;
		}
	}
	@OnClose
	public void onClose(Session session, CloseReason closeReson){
		// TODO remove train from base
		sessions.remove(session);
		LOG.info("Session removed, id:"+session.getId());
	}
}
