package com.bentyn.traincoll.commons.algorithms.test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.bentyn.traincoll.commons.algorithms.AbstractCDAlgorithm;
import com.bentyn.traincoll.commons.algorithms.BasicCDAlgorithm;
import com.bentyn.traincoll.commons.algorithms.CDAlgorithmResponse;
import com.bentyn.traincoll.commons.algorithms.FixedSizeQueue;
import com.bentyn.traincoll.commons.data.TrainData;

public class Test {

	public static void main(String[] args) {
		TrainData first = new TrainData();
		TrainData second = new TrainData();

		long timestamp = System.currentTimeMillis();
		Calendar cal= GregorianCalendar.getInstance();
		cal.setTimeInMillis(timestamp);
		System.out.println(cal.getTime());


		first.setHeading(45);
		first.setSpeed(10);
		first.setLatitude(0);
		first.setLongitude(0);
		first.setTimestamp(timestamp);

		second.setHeading(135);
		second.setSpeed(9.9);
		second.setLatitude(1);
		second.setLongitude(0);
		second.setTimestamp(timestamp);

		AbstractCDAlgorithm algorithm;

		algorithm = new BasicCDAlgorithm(100000, 1000,0.01);

		FixedSizeQueue<TrainData> firstData = new FixedSizeQueue<TrainData>(10);
		FixedSizeQueue<TrainData> secondData = new FixedSizeQueue<TrainData>(10);
		firstData.add(first);
		secondData.add(second);

		CDAlgorithmResponse response = algorithm.checkCollision(firstData, secondData);
		System.out.println(response);
		cal= GregorianCalendar.getInstance();
		cal.setTimeInMillis(response.getCollisionTimestamp());
		System.out.println(cal.getTime());
	}
}
