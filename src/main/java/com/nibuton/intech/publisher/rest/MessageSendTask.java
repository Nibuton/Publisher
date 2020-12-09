package com.nibuton.intech.publisher.rest;

import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Класс, инкапсулирующий в себе задание по отправке сообщения для запуска в потоке.
 * @author nibuton
 */

import com.nibuton.intech.publisher.entity.Message;

/**
 * Класс для создания бесконечного задания на отсылку сообщения в потоке. Берет сообщение из очереди,
 * затем вызывает метод Send Sender а и говорит потоку спать 15 секунд
 * @author nibuton
 *
 */
public class MessageSendTask implements Runnable{
	
	private Sender<Message> sender;
	private BlockingQueue<Message> queue;
	
	Logger logger = LoggerFactory.getLogger(MessageSendTask.class);
	
	@Autowired
	public MessageSendTask(Sender<Message> sender, BlockingQueue<Message> queue) {
		this.sender = sender;
		this.queue = queue;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Message msg = queue.take();
				logger.debug("Took from queue: " + msg);
				sender.send(msg);
				logger.info(msg + " have been sent");
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}

}
