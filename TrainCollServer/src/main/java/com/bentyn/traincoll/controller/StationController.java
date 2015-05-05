package com.bentyn.traincoll.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.imageio.spi.RegisterableService;
import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bentyn.traincoll.commons.communication.Message;
import com.bentyn.traincoll.commons.communication.MessageType;
import com.bentyn.traincoll.commons.data.TrainData;
import com.bentyn.traincoll.communication.CollisionWebSocket;
import com.bentyn.traincoll.model.SpatialTrainData;
import com.bentyn.traincoll.model.StationData;


public class StationController {

	private final static Logger LOG = LoggerFactory.getLogger(StationController.class); 
	
	@Autowired
	private MessageController messageController;
	
	private Set<StationData> stations = new HashSet<>();
	private Map<Session,StationData> sessionMapping = new HashMap<>();
	
	public void registerStation(StationData station,Session session){
		station.setSession(session);
		stations.add(station);
		sessionMapping.put(session, station);
	}
	
	public void updateTrainPosition(SpatialTrainData train) throws IOException{
		// TODO update trains in range only
		for (StationData station:stations){
			messageController.sendMessage(station.getSession(), train);
		}
	}
	
	public void unregisterStation(Session session){
	
		StationData station = sessionMapping.get(session);
		if(station != null){
			stations.remove(station);
			sessionMapping.remove(session);
			LOG.info("Station with id: "+station.getId()+" was removed");
		}
	}

	public void deleteTrain(SpatialTrainData train){
		for (StationData station:stations){
			messageController.sendMessage(station.getSession(), MessageType.REMOVE_TRAIN,train);
		}
	}
	
	public Map<Session, StationData> getSessionMapping() {
		return sessionMapping;
	}

	public void setSessionMapping(Map<Session, StationData> sessionMapping) {
		this.sessionMapping = sessionMapping;
	}
	
	
}
