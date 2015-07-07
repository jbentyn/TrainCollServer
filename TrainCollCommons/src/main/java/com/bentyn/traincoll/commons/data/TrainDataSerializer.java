package com.bentyn.traincoll.commons.data;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class TrainDataSerializer<T  extends TrainData> implements JsonDeserializer<T>,JsonSerializer<T>{

	private Class<T> clazz;
	
	
	public TrainDataSerializer(Class<T> clazz) {
		super();
		this.clazz = clazz;
	}

	@Override
	public JsonElement serialize(TrainData train, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", train.getId());
		jsonObject.addProperty("latitude", train.getLatitude());
		jsonObject.addProperty("longitude", train.getLongitude());
		jsonObject.addProperty("speed", train.getSpeed());
		jsonObject.addProperty("heading", train.getHeading());
		jsonObject.addProperty("timestamp", train.getTimestamp());
		return jsonObject;
	}

	@Override
	public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		T train = null;
		try {
			train = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new JsonParseException(e);
		}
	//	TrainData train = new TrainData();
		JsonObject jsonObject=json.getAsJsonObject();
		train.setId(jsonObject.get("id").getAsString());
		train.setLatitude(jsonObject.get("latitude").getAsDouble());
		train.setLongitude(jsonObject.get("longitude").getAsDouble());
		train.setSpeed(jsonObject.get("speed").getAsDouble());
		train.setHeading(jsonObject.get("heading").getAsDouble());
		train.setTimestamp(jsonObject.get("timestamp").getAsLong());
		return  train;
	}

	
}
