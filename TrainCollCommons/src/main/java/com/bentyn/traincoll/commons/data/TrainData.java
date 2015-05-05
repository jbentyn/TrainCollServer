package com.bentyn.traincoll.commons.data;

import java.io.Serializable;

public class TrainData implements Serializable{

	private static final long serialVersionUID = 9205510814439342008L;

	private String id;
	private double latitude;
	private double longitude;
	private double speed;
	private double heading;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getHeading() {
		return heading;
	}
	public void setHeading(double heading) {
		this.heading = heading;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TrainData [id=").append(id).append(", latitude=").append(latitude).append(", longitude=").append(longitude).append(", speed=").append(speed).append(", heading=")
				.append(heading).append("]");
		return builder.toString();
	}
	
	
}
