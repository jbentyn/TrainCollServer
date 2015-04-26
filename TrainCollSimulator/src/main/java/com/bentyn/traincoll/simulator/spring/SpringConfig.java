package com.bentyn.traincoll.simulator.spring;

import javax.websocket.ClientEndpointConfig;
import javax.websocket.MessageHandler;

import org.glassfish.tyrus.client.ClientManager;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.bentyn.traincoll.commons.communication.Message;
import com.bentyn.traincoll.commons.communication.MessageSerializer;
import com.bentyn.traincoll.commons.data.EventData;
import com.bentyn.traincoll.commons.data.EventDataSerializer;
import com.bentyn.traincoll.simulator.TrainEndpoint;
import com.bentyn.traincoll.simulator.TrainMessageHandler;
import com.bentyn.traincoll.simulator.gpx.GpxParser;
import com.bentyn.traincoll.simulator.gui.EndpointWindow;
import com.bentyn.traincoll.simulator.gui.EventButtonListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Configuration
public class SpringConfig {
	
	@Bean
	public ClientManager getClientManager(){
        ClientManager client = ClientManager.createClient();
        return client;
	}
	
	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public  TrainEndpoint getTrainEndpoint() {
		TrainEndpoint endpoint = new TrainEndpoint();
		
		return endpoint;
	}
	
	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public TrainMessageHandler getMessageHandler(){
		return new TrainMessageHandler();
	}
	
	
	@Bean
	public Gson getGson(){
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Message.class, new MessageSerializer());
		gsonBuilder.registerTypeAdapter(EventData.class, new EventDataSerializer());
		return gsonBuilder.create();
	}
	
	@Bean
	public ThreadPoolTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		pool.setCorePoolSize(5);
		pool.setMaxPoolSize(10);
		pool.setWaitForTasksToCompleteOnShutdown(true);
		return pool;
	}
	
	
	//GUI
	@Bean
	public EndpointWindow getEndpointWindow(){
		return new EndpointWindow();
	}
	
	@Bean
	public EventButtonListener getEventButtonListener(){
		return new EventButtonListener();
	}
	
	
}
