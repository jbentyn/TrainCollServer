package com.bentyn.traincoll.model;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.paralleluniverse.spacebase.AABB;
import co.paralleluniverse.spacebase.ElementUpdater;
import co.paralleluniverse.spacebase.SpaceBase;
import co.paralleluniverse.spacebase.SpaceBaseBuilder;
import co.paralleluniverse.spacebase.SpatialModifyingVisitor;
import co.paralleluniverse.spacebase.SpatialToken;

import com.bentyn.traincoll.commons.data.TrainData;

public class TrainBase {
	private final static Logger LOG = LoggerFactory.getLogger(TrainBase.class); 
	
	private SpaceBase<TrainData> base;
	private Map<String,SpatialToken> idMapping = new HashMap<>();
	private static final String BASE_NAME="space-1";
	
	public TrainBase(){
		base  = new SpaceBaseBuilder().setDimensions(2).build(BASE_NAME);
		LOG.debug("Base created");
	}

	public void insertOrUpdate(final TrainData  trainData){
		//TODO nie updatuje tylko wrzuca nowe/ AABB z encji na podstawie lat/long
		SpatialToken token = idMapping.get(trainData.getId());
		if (token == null){
			//insert
			idMapping.put(trainData.getId(), 
					base.insert(trainData, AABB.create(trainData.getLatitude(), trainData.getLatitude(), trainData.getLongitude(), trainData.getLongitude())));
		}else{
			base.update(token, new SpatialModifyingVisitor<TrainData>() {

				public void visit(ElementUpdater<TrainData> updater) {
					// modify updater.element()
					updater.elem().setLatitude(trainData.getLatitude());
					updater.elem().setLongitude(trainData.getLongitude());
					updater.elem().setSpeed(trainData.getSpeed());
					updater.elem().setHeading(trainData.getHeading());
					updater.update(AABB.create(trainData.getLatitude(), trainData.getLatitude(), trainData.getLongitude(), trainData.getLongitude()));
				}


				@Override
				public void done() {
				}
			});
		}
	}



	public SpaceBase<TrainData> getBase() {
		return base;
	}

	public void setBase(SpaceBase<TrainData> base) {
		this.base = base;
	}

}
