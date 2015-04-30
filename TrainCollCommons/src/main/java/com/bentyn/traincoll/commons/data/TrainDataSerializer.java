package com.bentyn.traincoll.commons.data;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class TrainDataSerializer implements JsonDeserializer<TrainData>,JsonSerializer<TrainData>{

	@Override
	public JsonElement serialize(TrainData train, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", train.getId());
		jsonObject.addProperty("latitude", train.getLatitude());
		jsonObject.addProperty("longitude", train.getLongitude());
		jsonObject.addProperty("speed", train.getSpeed());
		jsonObject.addProperty("heading", train.getHeading());
		return jsonObject;
	}

	@Override
	public TrainData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		TrainData train = new TrainData();
		JsonObject jsonObject=json.getAsJsonObject();
		train.setId(jsonObject.get("id").getAsString());
		train.setLatitude(jsonObject.get("latitude").getAsDouble());
		train.setLongitude(jsonObject.get("longitude").getAsDouble());
		train.setSpeed(jsonObject.get("speed").getAsDouble());
		train.setHeading(jsonObject.get("heading").getAsDouble());
		return train;
	}

	
}
