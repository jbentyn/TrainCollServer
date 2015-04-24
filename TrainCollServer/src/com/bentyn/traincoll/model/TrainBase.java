package com.bentyn.traincoll.model;

import java.util.HashMap;
import java.util.Map;

import com.bentyn.traincoll.commons.data.TrainData;
import com.bentyn.traincoll.config.Test;

import co.paralleluniverse.spacebase.AABB;
import co.paralleluniverse.spacebase.SpaceBase;
import co.paralleluniverse.spacebase.SpaceBaseBuilder;
import co.paralleluniverse.spacebase.SpatialToken;

public class TrainBase {

	private SpaceBase<TrainData> base;
	private Map<String,SpatialToken> idMapping = new HashMap<>();
	
	public TrainBase(){
		base  = new SpaceBaseBuilder().setDimensions(2).build("space-1");
		System.out.println("Base created");
	}
	
	public void insertOrUpdate(TrainData  trainData){
		idMapping.put(trainData.getId(), base.insert(trainData, AABB.create(8.0, 8.0, 7.5, 7.5)));
	}
	

	public SpaceBase<TrainData> getBase() {
		return base;
	}

	public void setBase(SpaceBase<TrainData> base) {
		this.base = base;
	}
	
}
