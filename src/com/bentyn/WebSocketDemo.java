package com.bentyn;

import java.io.IOException;

import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/echo")
public class WebSocketDemo {

	public void echoTextMessage(Session session,String msg, boolean last){
		try {
			if (session.isOpen()){
				session.getBasicRemote().sendText(msg,last);
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
