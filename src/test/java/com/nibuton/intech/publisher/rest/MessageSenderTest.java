package com.nibuton.intech.publisher.rest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Locale;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseFactory;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.params.HttpParams;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import com.nibuton.intech.publisher.entity.Message;

class MessageSenderTest {
	
	/*@Mock
	static ObjectToJsonBuilder<Message> objectToJsonBuilder;
	
	@Mock
	static HttpClient httpClient;
	
	@Test
	void executeWasCalled() {
		Sender<Message> sender = new MessageSender(objectToJsonBuilder);
		ReflectionTestUtils.setField(sender, "postUrl", "http://localhost:8000");
		String jsonString = "{\"id\":0,\"msisdn\":1,\"timestamp\":100,\"action\":\"test\"}";
		Mockito.when(objectToJsonBuilder.buildJsonString(Mockito.any())).thenReturn(jsonString);
		try {
			Mockito.when(httpClient.execute(Mockito.any())).thenReturn(null);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		try {
			Mockito.verify(httpClient).execute(Mockito.any());
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}
	*/
}
