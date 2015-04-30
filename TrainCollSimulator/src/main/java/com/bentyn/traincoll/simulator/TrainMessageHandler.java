package com.bentyn.traincoll.simulator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Future;

import javax.websocket.MessageHandler;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;

import com.bentyn.traincoll.commons.communication.Message;
import com.bentyn.traincoll.commons.communication.MessageType;
import com.bentyn.traincoll.commons.data.TrainData;
import com.bentyn.traincoll.simulator.gui.EndpointWindow;
import com.google.gson.Gson;

public class TrainMessageHandler implements MessageHandler.Whole<String>{
	
	@Autowired
	private Gson gson;
	
	@Autowired
	private EndpointWindow window;
	
	private Session session;
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	@Override
	public void onMessage(String message) {
		Message msg =  gson.fromJson(message, Message.class);
		printMessage(msg);
	}

	private void sendWholeAsyncMessage(Message message){
	    Future<Void> future = session.getAsyncRemote().sendText(gson.toJson(message));
	}
	
	public void sendPositionUpdate(TrainData trainData){
		Message message = new Message();
		message.setType(MessageType.POSITION_UPDATE);
		message.setData(gson.toJsonTree(trainData));
		 sendWholeAsyncMessage(message);
	}
	
	private void printMessage(Message msg){
		Date now= new Date();
		window.eventsTextArea.append(dateFormat.format(now)+" | "+msg+"\n");
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
	
	
}
