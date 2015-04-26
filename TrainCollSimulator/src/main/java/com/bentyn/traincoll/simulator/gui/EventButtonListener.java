package com.bentyn.traincoll.simulator.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.websocket.ClientEndpointConfig;
import javax.websocket.DeploymentException;

import org.glassfish.tyrus.client.ClientManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.bentyn.traincoll.simulator.TrainEndpoint;

public class EventButtonListener implements ActionListener {

	@Autowired
	EndpointWindow window;
	@Autowired
	ClientManager client;
	@Autowired
	TrainEndpoint endpoint;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == window.startBtn){
				connect();
			}
		}
		catch(DeploymentException| IOException| URISyntaxException ex){
			ex.printStackTrace();
		}
	}

	private void connect() throws DeploymentException, IOException, URISyntaxException{
		final ClientEndpointConfig cec = ClientEndpointConfig.Builder.create().build();
		System.out.println("before connect");
		client.connectToServer(endpoint,cec,new URI("ws://localhost:8080/TrainCollServer/collision"));
		System.out.println("after connect");
	}
}
