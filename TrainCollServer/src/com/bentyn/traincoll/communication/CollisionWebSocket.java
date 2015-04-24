package com.bentyn.traincoll.communication;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import co.paralleluniverse.spacebase.AABB;
import co.paralleluniverse.spacebase.SpatialToken;

import com.bentyn.traincoll.commons.communication.Message;
import com.bentyn.traincoll.commons.data.EventData;
import com.bentyn.traincoll.commons.data.TrainData;
import com.bentyn.traincoll.config.Test;
import com.bentyn.traincoll.controller.TrainController;
import com.bentyn.traincoll.model.TrainBase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@ServerEndpoint(value="/collision", configurator = SpringConfigurator.class)
public class CollisionWebSocket {

	@Autowired
	private Test test;
	
	@Autowired
	private TrainController trainController;
	
	@Autowired
	private Gson gson;
	
	private Set<Session> sessions = new HashSet<>();
	
	@OnOpen
	public void onOpen(Session session, EndpointConfig config){
		System.out.println(session);
		System.out.println("ConnectionOpen");
		sessions.add(session);
	}
	@OnMessage
	public void onTextMessage(Session session,String msg, boolean last) throws IOException{
		Gson gson = new GsonBuilder().create();
		Message message =  gson.fromJson(msg, Message.class);
		
		switch(message.getType()){
		case POSITION_UPDATE:
			TrainData train = gson.fromJson(message.getData(), TrainData.class);
			trainController.updateTrainPosition(train);
			// send posiotion update
			break;
		case EVENT:
			EventData event =  gson.fromJson(message.getData(),EventData.class);
			System.out.println(event);
			trainController.sendToAll(event, sessions);
			break;
		default:
			break;
		}
	}
	
}
