package com.bentyn.traincoll.commons.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.bentyn.traincoll.commons.data.TrainData;
import com.bentyn.traincoll.commons.utils.AlgorithmUtils;
import com.bentyn.traincoll.commons.utils.GeoUtils;

public class MeanCDAlgorithm extends AbstractCDAlgorithm {
	private class MeanData {
		double lat;
		double lon;
		double speed;
		double acceleration;
		double heading;

	}

	private double precision;
	private long timeNumericPrecision;
	private double speedNumericPrecision;
	private double accNumericPrecision;

	public MeanCDAlgorithm(double precision, long timeNumericPrecision, double speedNumericPrecision, double accNumericPrecision) {
		super();
		this.precision = precision;
		this.timeNumericPrecision = timeNumericPrecision;
		this.speedNumericPrecision = speedNumericPrecision;
		this.accNumericPrecision = accNumericPrecision;
	}


	@Override
	public CDAlgorithmResponse checkCollision(FixedSizeQueue<TrainData> firstTrainData, FixedSizeQueue<TrainData> secondTrainData) {

		MeanData firstMean= getMeanData(firstTrainData);
		MeanData secondMean = getMeanData(secondTrainData);

		double baseLat=0.0;
		double baseLon=0.0;

		long currentTime =firstTrainData.getLast().getTimestamp() > secondTrainData.getLast().getTimestamp()
				? firstTrainData.getLast().getTimestamp()
						:secondTrainData.getLast().getTimestamp() 
						;

				firstMean.speed = AlgorithmUtils.kphToMps(firstMean.speed);
				secondMean.speed = AlgorithmUtils.kphToMps(secondMean.speed);
				firstMean.heading= Math.toRadians(firstMean.heading);
				secondMean.heading = Math.toRadians(secondMean.heading);

				double firstXSpeed= Math.sin(firstMean.heading)*firstMean.speed;
				double secondXSpeed = Math.sin(secondMean.heading)*secondMean.speed;
				double firstXAcc= Math.sin(firstMean.heading)*firstMean.acceleration;
				double secondXAcc = Math.sin(secondMean.heading)*secondMean.acceleration;

				double timeX = getTimeAxisAlign(baseLon, firstMean.lon, secondMean.lon, firstXSpeed, secondXSpeed, firstXAcc, secondXAcc, currentTime );

				double firstYSpeed= Math.cos(firstMean.heading)*firstMean.speed;
				double secondYSpeed = Math.cos(secondMean.heading)*secondMean.speed;
				double firstYAcc= Math.cos(firstMean.heading)*firstMean.acceleration;
				double secondYAcc = Math.cos(secondMean.heading)*secondMean.acceleration;

				double timeY = getTimeAxisAlign(baseLat, firstMean.lat, secondMean.lat, firstYSpeed, secondYSpeed, firstYAcc, secondYAcc, currentTime );


				CDAlgorithmResponse response = new CDAlgorithmResponse();
				double overlappingTime = getOverlappingTime(firstMean.speed, secondMean.speed, firstMean.acceleration, secondMean.acceleration);
				double timeDelta=Math.abs(timeX-timeY);

				if (timeX >=0 && timeY >=0){
					if (timeDelta < timeNumericPrecision || timeDelta <=overlappingTime || timeX == 0 || timeY == 0){
						response.setDetected(true);
						response.setCollisionTimestamp(currentTime+Math.round(timeX==0?timeY:timeX));
					}
				}

				return response;
	}


	private double getTimeAxisAlign(double globalBasePos, double firstPosition, double secondPosition,
			double firstSpeed,double secondSpeed,double firstAcc,double secondAcc, long currentTimestamp){
		double pos1=GeoUtils.distFrom(globalBasePos, 0, firstPosition, 0);
		pos1= firstPosition>0 ? pos1 : -pos1 ;

		double pos2=GeoUtils.distFrom(globalBasePos, 0, secondPosition, 0);
		pos2= secondPosition>0 ? pos2 : -pos2 ;
		double posDelta = pos1-pos2;
		double speedDelta = firstSpeed-secondSpeed;
		double accDelta= (firstAcc-secondAcc);
		if(Math.abs(speedDelta) <speedNumericPrecision ){
			speedDelta=0;
		}
		if (Math.abs(accDelta)<accNumericPrecision ){
			accDelta=0;
		}
		double[] times=AlgorithmUtils.quadric(accDelta/2.0, speedDelta, posDelta);
		if (times.length > 0){
			if (times.length == 1){
				return times[0] *1000;
			}

			double t1 = times[0]*1000;
			double t2 = times[1]*1000;

			if (t1>0 && t2>0){
				return (t1 > t2) ? t2: t1;
			}
			if (t1 >0 && t2<=0){
				return t1;
			}
			if (t2>0 && t1<=0){
				return t2;
			}
		}
		// no collision time 
		return -1;
	}

	private double getOverlappingTime (double firstSpeed ,double secondSpeed, double firstAcc,double secondAcc){
		double resultsFirst[] = AlgorithmUtils.quadric(firstAcc /2.0,firstSpeed, -precision);
		double resultsSecond[] = AlgorithmUtils.quadric(secondAcc /2.0,secondSpeed, -precision);
		double max = 0;
		max = getMax(resultsFirst, max);
		max = getMax(resultsSecond, max);
		//result in milis
		return max*1000;
	}

	private double getMax (double[] array,double max){
		double result = max;
		for(int i=0 ;i<array.length;i++){
			if (array[i]>result){
				result=array[i];
			}
		}
		return result;
	}

	private MeanData getMeanData (FixedSizeQueue<TrainData> trainData){
		MeanData result = new MeanData();
		Iterator<TrainData> iter = trainData.iterator(); 
		TrainData previous = null;
		while( iter.hasNext() ){
			TrainData train=iter.next();
			result.lat +=train.getLatitude();
			result.lon +=train.getLongitude();
			result.speed += train.getSpeed();
			result.heading +=train.getHeading();
			if (previous != null){
				// get acceleration in m/s^2
				result.acceleration += 
						(AlgorithmUtils.kphToMps(train.getSpeed()-previous.getSpeed()))
						/
						((train.getTimestamp()-previous.getTimestamp()) * 1000); 	
			}
			previous=train;
		}

		result.lat /=trainData.size();
		result.lon /=trainData.size();
		result.speed /=trainData.size();
		result.heading /=trainData.size();
		if (trainData.size() > 1){
			result.acceleration /=trainData.size()-1;
		}
		return result;
	}


}
