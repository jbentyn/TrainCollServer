package com.bentyn.traincoll.model;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class StationSerializer <T  extends StationData> implements JsonDeserializer<T>,JsonSerializer<T> {
	
	private Class<T> clazz;
	
	
	public StationSerializer(Class<T> clazz) {
		super();
		this.clazz = clazz;
	}

	@Override
	public JsonElement serialize(StationData station, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", station.getId());
		jsonObject.addProperty("latitude", station.getLatitude());
		jsonObject.addProperty("longitude", station.getLongitude());
		return jsonObject;
	}

	@Override
	public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		T station = null;
		try {
			station = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new JsonParseException(e);
		}
		JsonObject jsonObject=json.getAsJsonObject();
		station.setId(jsonObject.get("id").getAsString());
		station.setLatitude(jsonObject.get("latitude").getAsDouble());
		station.setLongitude(jsonObject.get("longitude").getAsDouble());
		return  station;
	}
}
