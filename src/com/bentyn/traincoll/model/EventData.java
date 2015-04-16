package com.bentyn.traincoll.model;

import java.lang.reflect.Type;

import com.bentyn.traincoll.communication.MessagePart;
import com.bentyn.traincoll.communication.Message;
import com.bentyn.traincoll.communication.MessageType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class EventData implements MessagePart{

	private String priority;
	private String text;
		
	@Override
	public MessageType getType() {
		return MessageType.EVENT;
	}


	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EventData [priority=").append(priority).append(", text=").append(text).append("]");
		return builder.toString();
	}
	
}
