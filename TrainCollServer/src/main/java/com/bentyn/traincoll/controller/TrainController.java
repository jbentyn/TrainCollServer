package com.bentyn.traincoll.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import co.paralleluniverse.geodesy.Datum;
import co.paralleluniverse.geodesy.GeoLocation;
import co.paralleluniverse.spacebase.AABB;
import co.paralleluniverse.spacebase.ElementUpdater;
import co.paralleluniverse.spacebase.SpaceBase;
import co.paralleluniverse.spacebase.SpaceBaseBuilder;
import co.paralleluniverse.spacebase.SpatialModifyingVisitor;
import co.paralleluniverse.spacebase.SpatialQueries;
import co.paralleluniverse.spacebase.SpatialSetVisitor;
import co.paralleluniverse.spacebase.SpatialToken;
import co.paralleluniverse.spacebase.geo.GeoQueries;

import com.bentyn.traincoll.commons.data.TrainData;
import com.bentyn.traincoll.model.SpatialTrainData;

public class TrainController {
	private final static Logger LOG = LoggerFactory.getLogger(TrainController.class); 

	@Autowired
	private MessageController messageController;

	private SpaceBase<SpatialTrainData> base;
	private Map<String,SpatialTrainData> idMapping = new HashMap<>();
	private Map<Session,SpatialTrainData> sessionMapping = new HashMap<>();
	private static final String BASE_NAME="space-1";
	public static final double COLLISION_CHECKING_RANGE=10000d;

	public TrainController(){
		base  = new SpaceBaseBuilder().setDimensions(2).build(BASE_NAME);
		LOG.debug("Base created");
	}

	public void insertOrUpdate(final SpatialTrainData  trainData,Session session){
		SpatialTrainData oldTrainData=  idMapping.get(trainData.getId());
		if (oldTrainData == null){
			//insert
			SpatialToken token = base.insert(trainData, trainData.getAABB());
			trainData.setToken(token);
			trainData.setSession(session);
			idMapping.put(trainData.getId(), trainData);
			sessionMapping.put(session, trainData);
		}else{
			base.update(oldTrainData.getToken(), new SpatialModifyingVisitor<SpatialTrainData>() {

				public void visit(ElementUpdater<SpatialTrainData> updater) {
					// modify updater.element()
					updater.elem().setLatitude(trainData.getLatitude());
					updater.elem().setLongitude(trainData.getLongitude());
					updater.elem().setSpeed(trainData.getSpeed());
					updater.elem().setHeading(trainData.getHeading());
					updater.update(trainData.getAABB());
				}


				@Override
				public void done() {
				}
			});
		}
	}

	public void updatePosition(final SpatialTrainData train, double rangeInMeters){

		base.query(GeoQueries.georange(Datum.WGS84,train.getAABB(),rangeInMeters),new SpatialSetVisitor<SpatialTrainData>() {
			public void visit(Set<SpatialTrainData> result, Set<ElementUpdater<SpatialTrainData>> resForUpdate) {

				for(SpatialTrainData elem : result){
					if ( !train.getId().equals(elem.getId()) ){
						messageController.sendMessage(elem.getSession(), train);
					}
				}

			}
		});

	}


	public void remove(Session session){
		SpatialTrainData train=sessionMapping.get(session);
		if (train != null){
			base.delete(train.getToken());
			idMapping.remove(train.getId());
			sessionMapping.remove(session);
			LOG.info("Removed train with id: "+train.getId());
		}
	}



	public SpaceBase<SpatialTrainData> getBase() {
		return base;
	}

	public void setBase(SpaceBase<SpatialTrainData> base) {
		this.base = base;
	}

	public Map<Session, SpatialTrainData> getSessionMapping() {
		return sessionMapping;
	}

	public void setSessionMapping(Map<Session, SpatialTrainData> sessionMapping) {
		this.sessionMapping = sessionMapping;
	}


}
