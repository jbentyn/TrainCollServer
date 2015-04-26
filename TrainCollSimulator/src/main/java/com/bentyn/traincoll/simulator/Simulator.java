package com.bentyn.traincoll.simulator;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.swing.plaf.SliderUI;
import javax.websocket.ClientEndpointConfig;
import javax.websocket.DeploymentException;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;

import org.glassfish.tyrus.client.ClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.bentyn.traincoll.commons.communication.Message;
import com.bentyn.traincoll.commons.communication.MessageSerializer;
import com.bentyn.traincoll.commons.communication.MessageType;
import com.bentyn.traincoll.commons.data.EventData;
import com.bentyn.traincoll.commons.data.EventDataSerializer;
import com.bentyn.traincoll.simulator.gui.EndpointWindow;
import com.bentyn.traincoll.simulator.spring.SpringConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Simulator {

	private static CountDownLatch messageLatch;
	private static ApplicationContext context;
	
	public static void main(String[] args) throws DeploymentException, IOException, URISyntaxException, InterruptedException {
		 try {
	            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        } catch(ClassNotFoundException e) {
	        	e.printStackTrace();
	        } catch (InstantiationException e) {
	        	e.printStackTrace();
	        } catch (IllegalAccessException e) {
	        	e.printStackTrace();
	        } catch (UnsupportedLookAndFeelException e) {
	        	e.printStackTrace();
	        }
	    
	        System.setProperty("spring.profiles.active", "xml"); //$NON-NLS-1$ //$NON-NLS-2$
	        context = new AnnotationConfigApplicationContext(SpringConfig.class);
	        
	        
	        EventQueue.invokeLater(new Runnable(){
	            public void run(){
	                try{
	                    EndpointWindow window = context.getBean(EndpointWindow.class);
	                    window.init();
	                    window.setLocationRelativeTo(null);
	                    window.setVisible(true);


	                }catch(Exception e){
	                	e.printStackTrace();
	                }
	            }
	        });
	        
//		context = new AnnotationConfigApplicationContext(SpringConfig.class);
//
//		
	}
}
