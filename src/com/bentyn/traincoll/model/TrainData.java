package com.bentyn.traincoll.model;

import java.io.Serializable;

public class TrainData implements Serializable {

	private static final long serialVersionUID = 9205510814439342008L;

	private String id;
	private double latitude;
	private double longitude;
	
	
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
	
	
}
