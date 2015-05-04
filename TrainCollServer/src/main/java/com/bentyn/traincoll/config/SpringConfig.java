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
import com.bentyn.traincoll.controller.MessageController;
import com.bentyn.traincoll.controller.TrainBaseController;
import com.bentyn.traincoll.model.SpatialTrainData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Configuration
public class SpringConfig {
	
	
	@Bean
	public TrainBaseController getSpaceBaseTest(){
		return new TrainBaseController();
	}
	
	@Bean
	public MessageController getTrainController(){
		return new MessageController();
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
		gsonBuilder.registerTypeAdapter(TrainData.class, new TrainDataSerializer<TrainData>(TrainData.class));
		gsonBuilder.registerTypeAdapter(SpatialTrainData.class, new TrainDataSerializer<SpatialTrainData>(SpatialTrainData.class));
		return gsonBuilder.create();
	}
}
