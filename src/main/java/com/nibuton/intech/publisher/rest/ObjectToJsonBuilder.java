package com.nibuton.intech.publisher.rest;

public interface ObjectToJsonBuilder<T>{
	String buildJsonString(T obj);
}
