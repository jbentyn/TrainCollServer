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
		testData();
		System.out.println("Base created");
	}
	
	private void testData(){
		TrainData t1 = new TrainData();
		t1.setId("tr1");
		t1.setLatitude(10.10);
		t1.setLongitude(10.10);
		
		insert(t1);
		TrainData t2 = new TrainData();
		t2.setId("tr2");
		t2.setLatitude(11.10);
		t2.setLongitude(11.10);
		
		insert(t2);
	}
	
	public void insert(TrainData  trainData){
		idMapping.put(trainData.getId(), base.insert(trainData, AABB.create(8.0, 15.0, 7.5, 19.7)));
	}
	

	public SpaceBase<TrainData> getBase() {
		return base;
	}

	public void setBase(SpaceBase<TrainData> base) {
		this.base = base;
	}
	
}
