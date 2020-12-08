package com.nibuton.intech.publisher.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.nibuton.intech.publisher.entity.Message;

/**
 * Класс реализующий логику отправки сообщений.
 * 
 * @author nibuton
 *
 */
@Component
public class MessageSender implements Sender<Message>{
	
	private ObjectToJsonBuilder<Message> objectToJsonBuilder;
	
	private String postUrl;
	
	@Autowired
	public MessageSender(ObjectToJsonBuilder<Message> objectToJsonBuilder) {
		this.objectToJsonBuilder = objectToJsonBuilder;
	}

	/**
	 *@param msg Сообщение
	 */
	@Override
	public synchronized void send(Message msg) {
		try { 
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(postUrl);
			StringEntity postingString = new StringEntity(objectToJsonBuilder.buildJsonString(msg));
			post.setEntity(postingString);
			post.setHeader("Content-type", "application/json");
			System.out.println(msg);
			HttpResponse response = httpClient.execute(post);
		}
		catch (UnsupportedEncodingException e1){
			e1.printStackTrace();
		}
		catch (IOException e2) {
			e2.printStackTrace();
		}
	}

	public ObjectToJsonBuilder<Message> getObjectToJsonBuilder() {
		return objectToJsonBuilder;
	}

	public void setObjectToJsonBuilder(ObjectToJsonBuilder<Message> objectToJsonBuilder) {
		this.objectToJsonBuilder = objectToJsonBuilder;
	}

	public String getPostUrl() {
		return postUrl;
	}

	@Value("${post_url}")
	public void setPostUrl(String postUrl) {
		this.postUrl = postUrl;
	}
	
	

}
