package com.bentyn.traincoll.commons.communication;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class MessageSerializer implements JsonDeserializer<Message>,JsonSerializer<Message> {

	
	@Override
	public JsonElement serialize(Message  message, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject  jsonObject = new JsonObject();
		jsonObject.addProperty("type", message.getType().name());
		jsonObject.add("data", message.getData());
		return jsonObject;
	}


	@Override
	public Message  deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		Message message = new Message();
		JsonObject jsonObject=json.getAsJsonObject();
		message.setType(MessageType.valueOf(jsonObject.get("type").getAsString()));
		message.setData(jsonObject.get("data"));
		return message;
	}
}
