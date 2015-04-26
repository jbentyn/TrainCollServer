package com.bentyn.traincoll.simulator;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.websocket.MessageHandler;

import org.springframework.beans.factory.annotation.Autowired;

import com.bentyn.traincoll.commons.communication.Message;
import com.bentyn.traincoll.simulator.gui.EndpointWindow;
import com.google.gson.Gson;

public class TrainMessageHandler implements MessageHandler.Whole<String>{
	
	@Autowired
	private Gson gson;
	
	@Autowired
	private EndpointWindow window;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	@Override
	public void onMessage(String message) {
		Message msg =  gson.fromJson(message, Message.class);
		printMessage(msg);
	}

	private void printMessage(Message msg){
		Date now= new Date();
		window.eventsTextArea.append(dateFormat.format(now)+" | "+msg+"\n");
	}
}
