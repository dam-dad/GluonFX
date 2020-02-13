package com.moimah.hibernate.spring;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class InstantAdapter implements JsonSerializer<Instant>, JsonDeserializer<Instant> {
	
	private DateTimeFormatter formatter;
	
	public InstantAdapter() {
		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(Locale.getDefault()).withZone(ZoneId.systemDefault());
	}

	public JsonElement serialize(Instant src, Type typeOfSrc, JsonSerializationContext context) {
		if (src == null) return null;
		return new JsonPrimitive(formatter.format(src));
	}

	public Instant deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		if (json == null) return null;
		return LocalDate.parse(json.getAsString(), formatter).atStartOfDay().toInstant(ZoneOffset.UTC);
	}

}
