package com.bentyn.traincoll.commons.data;

import com.bentyn.traincoll.commons.communication.MessagePart;
import com.bentyn.traincoll.commons.communication.MessageType;

public class EventData implements MessagePart{

	//TODO add priority enum
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
