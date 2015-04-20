package com.bentyn.traincoll.commons.data;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class EventDataSerializer implements JsonDeserializer<EventData>,JsonSerializer<EventData>{
	@Override
	public JsonElement serialize(EventData event, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("text", event.getText());
		jsonObject.addProperty("status", event.getPriority());
		return jsonObject;
	}


	@Override
	public EventData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		EventData event = new EventData();
		JsonObject jsonObject=json.getAsJsonObject();
		event.setText(jsonObject.get("text").getAsString());
		event.setPriority(jsonObject.get("priority").getAsString());
		return event;
	}

}
