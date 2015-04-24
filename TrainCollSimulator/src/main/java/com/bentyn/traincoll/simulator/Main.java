package com.bentyn.traincoll.simulator;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.websocket.ClientEndpointConfig;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;

import org.glassfish.tyrus.client.ClientManager;

import com.bentyn.traincoll.commons.communication.Message;
import com.bentyn.traincoll.commons.communication.MessageSerializer;
import com.bentyn.traincoll.commons.communication.MessageType;
import com.bentyn.traincoll.commons.data.EventData;
import com.bentyn.traincoll.commons.data.EventDataSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



public class Main {
	
	 private static CountDownLatch messageLatch;
	    private static final String SENT_MESSAGE = "Hello World";
	public static void main(String[] args) {
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Message.class, new MessageSerializer());
		gsonBuilder.registerTypeAdapter(EventData.class, new EventDataSerializer());
		final Gson gson= gsonBuilder.create();
		
		try {
            messageLatch = new CountDownLatch(1);

            final ClientEndpointConfig cec = ClientEndpointConfig.Builder.create().build();

            ClientManager client = ClientManager.createClient();
            
            client.connectToServer(new Endpoint() {

                @Override
                public void onOpen(Session session, EndpointConfig config) {
                    try {
                        session.addMessageHandler(new MessageHandler.Whole<String>() {

                            @Override
                            public void onMessage(String message) {
                                System.out.println("Received message: "+message);
                                messageLatch.countDown();
                            }
                        });
                        Message msg = new Message();
                        EventData evet = new EventData();
                        evet.setPriority("High");
                        evet.setText("YUP");
                        msg.setData(gson.toJsonTree(evet));
                        msg.setType(MessageType.EVENT);
                        session.getBasicRemote().sendText(gson.toJson(msg));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
			,cec
			,new URI("ws://localhost:8080/TrainCollServer/collision"));
            messageLatch.await(100, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }

	}
}
