package com.utils;

import com.google.gson.Gson;

public class UnirestObjectMapper implements com.mashape.unirest.http.ObjectMapper {
	
	private Gson gson = new Gson();

	@Override
	public <T> T readValue(String json, Class<T> aClass) {
		try{
			return gson.fromJson(json, aClass);
		}catch(Exception e){			
			e.printStackTrace();			
		}
		return null;
		
	}

	@Override
	public String writeValue(Object value) {
		try{
			return gson.toJson(value);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}