package com.bentyn.traincoll.commons.algorithms;

import com.bentyn.traincoll.commons.data.TrainData;
import com.bentyn.traincoll.commons.utils.GeoUtils;

public class BasicCDAlgorithm extends AbstractCDAlgorithm {

	private double precision;
	private long timeNumericPrecision;
	private double speedNumericPrecision;

	public BasicCDAlgorithm(double precision,long timeNumericPrecision,double speedNumericPrecision) {
		super();
		this.precision = precision;
		this.timeNumericPrecision=timeNumericPrecision;
		this.speedNumericPrecision=speedNumericPrecision;
	}


	@Override
	public CDAlgorithmResponse checkCollision(FixedSizeQueue<TrainData> firstTrainData, FixedSizeQueue<TrainData> secondTrainData) {
		TrainData first= firstTrainData.getLast();
		TrainData second= secondTrainData.getLast();
		//TODO implement 
		//TODO problemy ze skrajymi wartoœciami
		double baseLat=0.0;
		double baseLon=0.0;

		double firstSpeedInMps=GeoUtils.kphToMps(first.getSpeed());
		double secondSpeedInMps=GeoUtils.kphToMps(second.getSpeed());

		double firstHeadingInRad = Math.toRadians(first.getHeading());
		double secondHeadingInRad = Math.toRadians(second.getHeading());

		double firstXSpeed= Math.sin(firstHeadingInRad)*firstSpeedInMps;
		double secondXSpeed = Math.sin(secondHeadingInRad)*secondSpeedInMps;
		double timeX= getTimeAxisAlign(baseLon, first.getLongitude(), second.getLongitude(), firstXSpeed, secondXSpeed);

		double firstYSpeed= Math.cos(firstHeadingInRad)*firstSpeedInMps;
		double secondYSpeed = Math.cos(secondHeadingInRad)*secondSpeedInMps;
		double timeY= getTimeAxisAlign(baseLat, first.getLatitude(), second.getLatitude(), firstYSpeed, secondYSpeed);

		CDAlgorithmResponse response = new CDAlgorithmResponse();

		if (timeX >=0 && timeY >=0){
			if (Math.abs(timeX-timeY) < timeNumericPrecision || timeX== 0 || timeY == 0){
				response.setDetected(true);
				response.setCollisionTimestamp(first.getTimestamp()+Math.round(timeX==0?timeY:timeX));
			}
		}


		return response;
	}

	private double getTimeAxisAlign(double globalBasePos, double firstPosition, double secondPosition,double firstSpeed,double secondSpeed){
		double pos1=GeoUtils.distFrom(globalBasePos, 0, firstPosition, 0);
		pos1= firstPosition>0 ? pos1 : -pos1 ;

		double pos2=GeoUtils.distFrom(globalBasePos, 0, secondPosition, 0);
		pos2= secondPosition>0 ? pos2 : -pos2 ;
		double speedDelta = firstSpeed-secondSpeed;
		double time=0.0;
		if (Math.abs(speedDelta) > speedNumericPrecision){
			time=(pos2 -pos1 -precision)/(firstSpeed-secondSpeed);
			//return time in milis
			time*=1000;
		}
		
		return time;
	}

}
