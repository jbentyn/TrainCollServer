package com.bentyn.traincoll.commons.algorithms;

import com.bentyn.traincoll.commons.data.TrainData;

public class BasicCDAlgorithm extends AbstractCDAlgorithm {

	@Override
	public boolean checkCollision(FixedSizeQueue<TrainData> firstTrainData, FixedSizeQueue<TrainData> secondTrainData) {
		TrainData first= firstTrainData.getLast();
		TrainData second= secondTrainData.getLast();
		//TODO implement 
		return true;
	}

}
