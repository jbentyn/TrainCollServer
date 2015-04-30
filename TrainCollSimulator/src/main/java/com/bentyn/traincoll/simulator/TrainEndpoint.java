package com.bentyn.traincoll.simulator;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;

import com.bentyn.traincoll.commons.communication.Message;
import com.bentyn.traincoll.commons.communication.MessageType;
import com.bentyn.traincoll.commons.data.EventData;
import com.google.gson.Gson;

public class TrainEndpoint  extends Endpoint{
	@Autowired
	private Gson gson;
	
	@Autowired
	private TrainMessageHandler messageHandler;

	@Override
	public void onOpen(final Session session, EndpointConfig arg1) {
		messageHandler.setSession(session);
		session.addMessageHandler(messageHandler);
	}
	@Override
	public void onClose(Session session, CloseReason closeReason) {
		// TODO Auto-generated method stub
		super.onClose(session, closeReason);
	}
	@Override
	public void onError(Session session, Throwable thr) {
		// TODO Auto-generated method stub
		super.onError(session, thr);
	}
	public TrainMessageHandler getMessageHandler() {
		return messageHandler;
	}
	public void setMessageHandler(TrainMessageHandler messageHandler) {
		this.messageHandler = messageHandler;
	}




}
