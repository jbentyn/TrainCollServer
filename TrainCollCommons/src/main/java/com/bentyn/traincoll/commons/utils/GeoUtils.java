package com.bentyn.traincoll.commons.utils;

import java.util.concurrent.TimeUnit;

public class GeoUtils {
	private static final double EARTH_RADIUS = 6371000; //meters
	/**
	 * Distance in  meters between 2 coordinates
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @return
	 */
	public static double distFrom(double lat1, double lng1, double lat2, double lng2) {
		
		double dLat = Math.toRadians(lat2-lat1);
		double dLng = Math.toRadians(lng2-lng1);
		double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
				Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
				Math.sin(dLng/2) * Math.sin(dLng/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double dist =  (EARTH_RADIUS * c);

		return dist;
	}
	/**
	 * get spped in km/h
	 * @param distnaceInMeters
	 * @param timeInMilis
	 * @return
	 */
	public static double speed(double distanceInMeters, long timeInMilis ){
		double hours=timeInMilis/1000.0/60.0/60.0;
		
		return distanceInMeters/1000 / hours;
	}
	
	/**
	 * calculate heading in degrees in rage (0,360)
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @return
	 */
	public static double heading(double lat1, double lng1, double lat2, double lng2){
		lat1=Math.toRadians(lat1);
		lng1=Math.toRadians(lng1);
		lat2=Math.toRadians(lat2);
		lng2=Math.toRadians(lng2);
		
		double dLon = lng2 - lng1;
		double y = Math.sin(dLon) * Math.cos(lat2);
		double x = Math.cos(lat1)*Math.sin(lat2) -
		        Math.sin(lat1)*Math.cos(lat2)*Math.cos(dLon);
		double heading = Math.atan2(y, x);
		heading=Math.toDegrees(heading);
		if (heading < 0) {
		       heading+= 360;
		  }
		return heading;
	}
	
}
