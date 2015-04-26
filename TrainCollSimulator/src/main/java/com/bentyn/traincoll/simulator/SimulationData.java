package com.bentyn.traincoll.simulator;

import com.bentyn.traincoll.commons.data.TrainData;

public class SimulationData {

	private TrainData trainData;
	private long timeDiff;
	
	public TrainData getTrainData() {
		return trainData;
	}
	public void setTrainData(TrainData trainData) {
		this.trainData = trainData;
	}
	public long getTimeDiff() {
		return timeDiff;
	}
	public void setTimeDiff(long timeDiff) {
		this.timeDiff = timeDiff;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SimulationData [trainData=").append(trainData).append(", timeDiff=").append(timeDiff).append("]");
		return builder.toString();
	}
	
	
	
}
