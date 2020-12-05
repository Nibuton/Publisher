package com.nibuton.intech.publisher.rest;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nibuton.intech.publisher.entity.Message;

@Component
public class ObjectToJsonBuilderImpl implements ObjectToJsonBuilder<Message> {

	public ObjectToJsonBuilderImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String buildJsonString(Message msg){
		GsonBuilder gsonBuilder  = new GsonBuilder();
		//gsonBuilder.excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT);
		Gson gson = gsonBuilder.create();
		String jsonString = gson.toJson(msg);
		return jsonString;
	}
}
