package com.nibuton.intech.publisher.config;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nibuton.intech.publisher.entity.Message;
import com.nibuton.intech.publisher.generator.MessageGenerator;
import com.nibuton.intech.publisher.rest.MessageSendTask;
import com.nibuton.intech.publisher.rest.ObjectToJsonBuilder;
import com.nibuton.intech.publisher.rest.Sender;

@Configuration
public class PublisherConfig {
	
	@Bean
	BlockingQueue<Message> returnBlockingQueue(){
		return new ArrayBlockingQueue<Message>(10,true);
	}
	
	@Bean
	CommandLineRunner startThreads(MessageGenerator generator, Sender<Message> sender, 
		ObjectToJsonBuilder<Message> objectToJsonBuilder, BlockingQueue<Message> queue) {
		
		Thread generatingThread = new Thread(generator);
		generatingThread.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for(int i = 0; i < 5; i++)
			executor.submit(new MessageSendTask(sender, queue));
		executor.shutdown();
	    return args -> {
	    };
	  }
	
	public PublisherConfig() {
	}

}
