package com.bentyn.traincoll.commons.communication;

import java.io.Serializable;

import com.google.gson.JsonElement;

public class Message implements Serializable{
	
	private static final long serialVersionUID = -9018131002071760279L;
	
	private MessageType type;
	private JsonElement data;
	
	public MessageType getType() {
		return type;
	}
	public void setType(MessageType type) {
		this.type = type;
	}
	public JsonElement getData() {
		return data;
	}
	public void setData(JsonElement data) {
		this.data = data;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Message [type=").append(type).append(", data=").append(data).append("]");
		return builder.toString();
	}
	
	
	
	
}
