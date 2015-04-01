package com.bentyn;

import java.io.IOException;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import co.paralleluniverse.spacebase.AABB;
import co.paralleluniverse.spacebase.SpatialToken;

import com.bentyn.configuration.Test;

@ServerEndpoint(value="/echo", configurator = SpringConfigurator.class)
public class WebSocketDemo {

	@Autowired
	Test test;
	
	@Autowired
	SpaceBaseTest spaceBase;
	
	public WebSocketDemo( ) {
		super();
	}

	@OnMessage
	public void echoTextMessage(Session session,String msg, boolean last){
		System.out.println(test.test());
		SpatialToken token = spaceBase.getBase().insert(test, AABB.create(8.0, 15.0, 7.5, 19.7));
		System.out.println(token);
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
