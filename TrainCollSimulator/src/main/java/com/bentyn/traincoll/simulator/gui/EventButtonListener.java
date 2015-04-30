package com.bentyn.traincoll.simulator.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JFileChooser;
import javax.websocket.ClientEndpointConfig;
import javax.websocket.DeploymentException;

import org.glassfish.tyrus.client.ClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.bentyn.traincoll.simulator.TrainEndpoint;
import com.bentyn.traincoll.simulator.TrainThread;
import com.bentyn.traincoll.simulator.gpx.GpxParser;
import com.bentyn.traincoll.simulator.gpx.schema.GpxType;

public class EventButtonListener implements ActionListener {

	@Autowired
	EndpointWindow window;
	@Autowired
	ClientManager client;
	@Autowired
	TrainEndpoint endpoint;
	
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	private TrainThread trainThread;
	private boolean isStarted = false;
	
	final JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == window.startBtn){
				if (isStarted){
					onStop();
				}else{
					onStart();
				}
			}
			if (e.getSource() == window.gpxBtn){
				chooseGpxFile();
			}
		}
		catch(DeploymentException| IOException| URISyntaxException ex){
			ex.printStackTrace();
		}
	}

	private void chooseGpxFile(){
		 int returnVal = fc.showOpenDialog(window);

	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = fc.getSelectedFile();
	            window.gpxTextField.setText(file.getAbsolutePath());
	        } else {

	        }
	}
	
	private void runTrainThread() {
		int timeSpeed = getInt(window.timeSpeedTextField.getText()); 
		String trainId = window.trainIdTextField.getText();
		GpxType gpx = GpxParser.fromFile(window.gpxTextField.getText());
		trainThread = new TrainThread(gpx,trainId ,timeSpeed,endpoint);
		
		 taskExecutor.execute(trainThread);
		 //taskExecutor.shutdown();
	}
	
	private void stopTrainThread(){
		trainThread.setStop(true);
	}
	private void connect() throws DeploymentException, IOException, URISyntaxException{
		final ClientEndpointConfig cec = ClientEndpointConfig.Builder.create().build();
		System.out.println("before connect");
		client.connectToServer(endpoint,cec,new URI("ws://localhost:8080/TrainCollServer/collision"));
		System.out.println("after connect");
	}
	
	private void disconnect(){
		client.shutdown();
	}
	private void onStart() throws DeploymentException, IOException, URISyntaxException{
		window.progressBar.setVisible(true);
		window.startBtn.setText("STOP");
		connect();
		runTrainThread();
		isStarted=true;
	}
	
	private void onStop(){
		window.progressBar.setVisible(false);
		window.startBtn.setText("START");
		stopTrainThread();
		disconnect();
		isStarted=false;
	}
	
	private int getInt(String in) {
		int d =0;
			d=Integer.parseInt(in);
		return d;
	}
}
