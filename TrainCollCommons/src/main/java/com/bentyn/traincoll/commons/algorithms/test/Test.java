package com.bentyn.traincoll.commons.algorithms.test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.bentyn.traincoll.commons.algorithms.AbstractCDAlgorithm;
import com.bentyn.traincoll.commons.algorithms.BasicCDAlgorithm;
import com.bentyn.traincoll.commons.algorithms.CDAlgorithmResponse;
import com.bentyn.traincoll.commons.algorithms.FixedSizeQueue;
import com.bentyn.traincoll.commons.algorithms.MeanCDAlgorithm;
import com.bentyn.traincoll.commons.data.TrainData;
import com.bentyn.traincoll.commons.utils.AlgorithmUtils;

public class Test {

	private static final double DTM=1/110574.61;
	
	public static void main(String[] args) {
//		TrainData first = new TrainData();
//		TrainData second = new TrainData();
//
//		long timestamp = System.currentTimeMillis();
//		Calendar cal= GregorianCalendar.getInstance();
//		cal.setTimeInMillis(timestamp);
//		System.out.println(cal.getTime());
//
//
//		first.setHeading(0);
//		first.setSpeed(10);
//		first.setLatitude(0);
//		first.setLongitude(0);
//		first.setTimestamp(timestamp);
//
//
//		second.setHeading(180);
//		second.setSpeed(10);
//		second.setLatitude(1);
//		second.setLongitude(0);
//		second.setTimestamp(timestamp);
//
		TrainData first1 = new TrainData();
		TrainData first2 = new TrainData();
		TrainData first3 = new TrainData();
//
//		TrainData second1 = new TrainData();
//		TrainData second2 = new TrainData();
//		TrainData second3 = new TrainData();
//
//		timestamp=1437168603125L;
//
		first1.setLatitude(51.69828417);first1.setLongitude(19.34739589);first1.setSpeed(92.97576716988475);first1.setHeading(13.240455372775967);	first1.setTimestamp(1437168603125L);
		first2.setLatitude(51.69851026);first2.setLongitude(19.34748172);first2.setSpeed(107.00742378582369);first2.setHeading(5.711155039447658);	first2.setTimestamp(1437168603226L);
		first3.setLatitude(51.69877625);first3.setLongitude(19.34752464);first3.setSpeed(51.202620856471455);first3.setHeading(8.972097569059171);	first3.setTimestamp(1437168603326L);
////		 
////		second1.setLatitude(51.6995742);second1.setLongitude(19.3476963);second1.setSpeed(85.34644121373695);second1.setHeading(3.5766416150862574);second1.setTimestamp(1437168603526L );
////		second2.setLatitude(51.69928162);second2.setLongitude(19.34761047);second2.setSpeed(59.520205208260364);second2.setHeading(10.304802025762044);second2.setTimestamp(1437168603626L);
////		second3.setLatitude(51.69902894);second3.setLongitude(19.34758901);second3.setSpeed(101.28827727768395);second.setHeading(3.0131768265721464);second3.setTimestamp(1437168603826L);
//
		first1.setLatitude(1);first1.setLongitude(0);first1.setSpeed(10);first1.setHeading(180);	first1.setTimestamp(0L);
		first2.setLatitude(1-10.5*DTM);first2.setLongitude(0);first2.setSpeed(10+1.0/3.6);first2.setHeading(180);	first2.setTimestamp(1000L);
		first3.setLatitude(1-22.5*DTM);first3.setLongitude(0);first3.setSpeed(10+2.0/3.6);first3.setHeading(180);	first3.setTimestamp(2000L);
//		 
//		second1.setLatitude(0);second1.setLongitude(0);second1.setSpeed(10);second1.setHeading(0);second1.setTimestamp(0L );
//		second2.setLatitude(0+10.5*DTM);second2.setLongitude(0);second2.setSpeed(10+1.0/3.6);second2.setHeading(0);second2.setTimestamp(1000L);
//		second3.setLatitude(0+22.5*DTM);second3.setLongitude(0);second3.setSpeed(10+2.0/3.6);second.setHeading(0);second3.setTimestamp(2000L);
//
//		
		FixedSizeQueue<TrainData> firstData = new FixedSizeQueue<TrainData>(10);
//		FixedSizeQueue<TrainData> secondData = new FixedSizeQueue<TrainData>(10);
//		//firstData.add(first);
//		//	secondData.add(second);
//
		firstData.add(first1);firstData.add(first2);firstData.add(first3);
//		secondData.add(second1);secondData.add(second2);secondData.add(second3);
//		AbstractCDAlgorithm algorithm;
//
//		//		algorithm = new BasicCDAlgorithm(100000, 1000,0.01);
//		algorithm = new MeanCDAlgorithm(1, 1000, 0.0000000000000001, 0.00000000000000000000001);
//
//		CDAlgorithmResponse response = algorithm.checkCollision(firstData, secondData);
//		System.out.println(response);
//		cal= GregorianCalendar.getInstance();
//		cal.setTimeInMillis(response.getCollisionTimestamp());
//		System.out.println(cal.getTime());


	////	Kalman k = new Kalman();
	//	FixedSizeQueue<TrainData> d=k.process(firstData);
	////	System.out.println(firstData);
	//	System.out.println(d);
		//System.out.println(Arrays.toString(AlgorithmUtils.quadric(2, 4, 2)));

	}
}
