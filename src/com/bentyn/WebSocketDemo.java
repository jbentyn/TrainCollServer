package com.bentyn;

import java.io.IOException;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import com.bentyn.configuration.Test;

@ServerEndpoint(value="/echo", configurator = SpringConfigurator.class)
public class WebSocketDemo {

	@Autowired
	Test test;
	
	public WebSocketDemo( ) {
		super();
	}

	@OnMessage
	public void echoTextMessage(Session session,String msg, boolean last){
		System.out.println(test.test());
		try {
			if (session.isOpen()){
				
				session.getBasicRemote().sendText(test.test(),last);
			}
		}
		catch( IOException e){
			
			try{
				session.close();
			}
			catch(IOException ex){
				
			}
		}
	}
}
