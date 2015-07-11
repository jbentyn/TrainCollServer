package com.bentyn.traincoll.commons.algorithms;

import com.bentyn.traincoll.commons.data.TrainData;

public abstract class AbstractCDAlgorithm {

	public abstract CDAlgorithmResponse checkCollision(FixedSizeQueue<TrainData> firstTrainData, FixedSizeQueue<TrainData> secondTrainData);
}