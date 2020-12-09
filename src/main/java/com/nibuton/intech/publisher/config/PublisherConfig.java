package com.nibuton.intech.publisher.config;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nibuton.intech.publisher.entity.Message;
import com.nibuton.intech.publisher.generator.MessageGenerator;
import com.nibuton.intech.publisher.rest.MessageSendTask;
import com.nibuton.intech.publisher.rest.Sender;

/**
 * Класс, содержащий Beans и логику запуска сообщений
 * @author nibuton
 *
 */
@Configuration
public class PublisherConfig {
	
	@Bean
	BlockingQueue<Message> returnBlockingQueue(){
		return new ArrayBlockingQueue<Message>(10,true);
	}
	
	/**
	 * <p>
	 * Метод содержит логику запуска. Генерируется начальное наполнение очереди сообщениями, после чего
	 * создается пул из 5 потоков, в каждый из которых передается задание на отправку сообщения.
	 * </p>
	 * @param generator Генератор сообщений
	 * @param sender Отправщик сообщений
	 * @param queue Блокирующая очередь сообщений для многопоточной отправки из FixedThreadPool
	 * @return empty args
	 */
	@Bean
	CommandLineRunner startThreads(MessageGenerator generator, Sender<Message> sender, 
		BlockingQueue<Message> queue) {
		
		Thread generatingThread = new Thread(generator);
		generatingThread.start();
		try {
			Thread.sleep(1000);
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
