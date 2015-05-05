package com.bentyn.traincoll.model;

import java.io.Serializable;

import javax.websocket.Session;

public class StationData implements Serializable{

	private static final long serialVersionUID = -1075679516367199671L;
	private String id;
	private double latitude;
	private double longitude;
	private Session session;

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
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	
	
}
