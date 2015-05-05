package com.bentyn.traincoll.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import co.paralleluniverse.spacebase.AABB;

import com.bentyn.traincoll.commons.communication.Message;
import com.bentyn.traincoll.commons.communication.MessagePart;
import com.bentyn.traincoll.commons.communication.MessageType;
import com.bentyn.traincoll.commons.data.TrainData;
import com.bentyn.traincoll.model.SpatialTrainData;
import com.google.gson.Gson;

public class MessageController {
	private final static Logger LOG = LoggerFactory.getLogger(MessageController.class); 

	@Autowired
	private Gson gson;
	
	public void sendMessage(Session session, MessagePart  messagePart) throws IOException {
		Message message = new Message();
		message.setData(gson.toJsonTree(messagePart));
		message.setType(messagePart.getType());
		session.getAsyncRemote().sendText(gson.toJson(message));
		LOG.debug("Message send: "+message);
	}
	
	
	public void sendToAll(MessagePart  messagePart, Set<Session> sessions) throws IOException{
		Message message = new Message();
		message.setData(gson.toJsonTree(messagePart));
		message.setType(messagePart.getType());
		Iterator <Session> iter=sessions.iterator();
		while (iter.hasNext()){
			iter.next().getAsyncRemote().sendText(gson.toJson(message));
		}
		LOG.debug("Message"+message+" send to "+sessions.size()+ " recivers");
	}
	
	//TODO sent to all but calee
}
