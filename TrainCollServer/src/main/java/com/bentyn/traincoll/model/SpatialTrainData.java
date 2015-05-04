package com.bentyn.traincoll.model;

import javax.websocket.Session;

import co.paralleluniverse.spacebase.AABB;
import co.paralleluniverse.spacebase.SpatialToken;

import com.bentyn.traincoll.commons.communication.MessagePart;
import com.bentyn.traincoll.commons.communication.MessageType;
import com.bentyn.traincoll.commons.data.TrainData;

public class SpatialTrainData extends TrainData implements MessagePart{
	private static final long serialVersionUID = -751740293167539321L;

	private Session session;
	private SpatialToken token;
	
	@Override
	public MessageType getType() {
		return MessageType.POSITION_UPDATE;
	}

	public AABB getAABB() {
		return AABB.create(getLatitude(),getLatitude(),getLongitude(),getLongitude());
	}
	
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}

	public SpatialToken getToken() {
		return token;
	}

	public void setToken(SpatialToken token) {
		this.token = token;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SpatialTrainData [session=").append(session).append(", token=").append(token).append(", getId()=").append(getId()).append(", getLatitude()=").append(getLatitude())
				.append(", getLongitude()=").append(getLongitude()).append(", getSpeed()=").append(getSpeed()).append(", getHeading()=").append(getHeading()).append("]");
		return builder.toString();
	}
	
	
	
}
