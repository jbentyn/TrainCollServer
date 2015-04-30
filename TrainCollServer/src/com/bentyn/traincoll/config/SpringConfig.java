package com.bentyn.traincoll.config;

import java.util.HashSet;
import java.util.Set;

import javax.websocket.Session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bentyn.traincoll.commons.communication.Message;
import com.bentyn.traincoll.commons.communication.MessageSerializer;
import com.bentyn.traincoll.commons.data.EventData;
import com.bentyn.traincoll.commons.data.EventDataSerializer;
import com.bentyn.traincoll.commons.data.TrainData;
import com.bentyn.traincoll.commons.data.TrainDataSerializer;
import com.bentyn.traincoll.controller.TrainController;
import com.bentyn.traincoll.model.TrainBase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Configuration
public class SpringConfig {
	
	@Bean
	public Test getTest(){
		System.out.println("TEST INIT");
		return new Test();
	}
	
	@Bean
	public TrainBase getSpaceBaseTest(){
		System.out.println("spaceBase INIT");
		return new TrainBase();
	}
	
	@Bean
	public TrainController getTrainController(){
		return new TrainController();
	}	
	
	@Bean(name="websocketSessions")
	public Set<Session> getSessions(){
		return new HashSet<Session>();
	}
	
	@Bean
	public Gson getGson(){
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Message.class, new MessageSerializer());
		gsonBuilder.registerTypeAdapter(EventData.class, new EventDataSerializer());
		gsonBuilder.registerTypeAdapter(TrainData.class, new TrainDataSerializer());
		return gsonBuilder.create();
	}
}
