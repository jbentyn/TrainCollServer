package com.bentyn.traincoll.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;

import com.bentyn.traincoll.commons.communication.Message;
import com.bentyn.traincoll.commons.communication.MessagePart;
import com.bentyn.traincoll.commons.data.TrainData;
import com.bentyn.traincoll.model.TrainBase;
import com.google.gson.Gson;

public class TrainController {

	@Autowired
	private TrainBase base; 
	@Autowired
	private Gson gson;
	
	public void updateTrainPosition(TrainData train){
		base.insertOrUpdate(train);
		System.out.println("Base size:"+base.getBase().size());
	}
	
	public void sendMessage(Session session, MessagePart  messagePart) throws IOException {
		Message message = new Message();
		message.setData(gson.toJsonTree(messagePart));
		message.setType(messagePart.getType());
		session.getBasicRemote().sendText(gson.toJson(message));
	}
	
	public void sendToAll(MessagePart  messagePart, Set<Session> sessions) throws IOException{
		Message message = new Message();
		message.setData(gson.toJsonTree(messagePart));
		message.setType(messagePart.getType());
		Iterator <Session> iter=sessions.iterator();
		while (iter.hasNext()){
			iter.next().getBasicRemote().sendText(gson.toJson(message));
		}
	}
}
