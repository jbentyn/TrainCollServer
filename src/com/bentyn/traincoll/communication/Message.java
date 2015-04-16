package com.bentyn.traincoll.communication;

import java.io.Serializable;
import java.lang.reflect.Type;

import com.bentyn.traincoll.model.EventData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

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
