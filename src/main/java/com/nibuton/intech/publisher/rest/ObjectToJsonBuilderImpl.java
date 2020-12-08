package com.nibuton.intech.publisher.rest;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nibuton.intech.publisher.entity.Message;

/**
 * Класс имплементация интерфейса для получения из экземпляра сообщения Json строку необходимого вида.
 * @author nibuton
 *
 */

@Component
public class ObjectToJsonBuilderImpl implements ObjectToJsonBuilder<Message> {

	public ObjectToJsonBuilderImpl() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param msg Сообщение
	 * @return Json строку заданного вида
	 */
	@Override
	public String buildJsonString(Message msg){
		GsonBuilder gsonBuilder  = new GsonBuilder();
		//gsonBuilder.excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT);
		Gson gson = gsonBuilder.create();
		String jsonString = gson.toJson(msg);
		return jsonString;
	}
}
