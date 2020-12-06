package com.nibuton.intech.publisher.rest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.nibuton.intech.publisher.entity.Message;

class ObjectToJsonBuilderImplTest {

	@Test
	void jsonBuildingIsCorrect() {
		ObjectToJsonBuilderImpl objectToJsonBuilderImpl = new ObjectToJsonBuilderImpl();
		Message msg = new Message(1, 100L, "test");
		String result = objectToJsonBuilderImpl.buildJsonString(msg);
		assertTrue(result.contains("\"msisdn\":1"));
		assertTrue(result.contains("\"timestamp\":100"));
		assertTrue(result.contains("\"action\":\"test\""));
	}

}
