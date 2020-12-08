package com.nibuton.intech.publisher.generator;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nibuton.intech.publisher.entity.Message;

/**
 * Класс генератора сообщений.
 * @author nibuton
 */
@Component
public class MessageGenerator implements Generator<Message>, Runnable{
	
	/**
	 * Массив доступных значений поля action. Поле Action содержится в сообщении.
	 * Inject через setter из application.properties
	 */
	private String[] actions;
	
	/**
	 * Блокирующая очередь, в которой хранятся сгенерированные сообщения
	 */
	
	private BlockingQueue<Message> queue;
	
	@Autowired
	public MessageGenerator(BlockingQueue<Message> queue) {
		this.queue = queue;
	}
	
	/**
	 * Метод для генерации сообщений
	 * @return Сгенерированный экземпляр Message
	 */
	@Override
	public Message generate() {
		Message msg = new Message();
		msg.setMsisdn(ThreadLocalRandom.current().nextInt(100000000));
		msg.setTimestamp(System.currentTimeMillis());
		msg.setAction(actions[ThreadLocalRandom.current().nextInt(2)]);
		return msg;
	}
	/**
	 * Метод для запуска генератора в потоке. Генерирует сообщение и кладет его в очередь.
	 */
	@Override
	public void run() {
		while(true) {
			try {
				Message msg = generate();
				queue.put(msg);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
	
	public String[] getActions() {
		return actions;
	}
	
	@Value("${actions}")
	public void setActions(String[] actions) {
		this.actions = actions;
	}

	public BlockingQueue<Message> getQueue() {
		return queue;
	}

	public void setQueue(BlockingQueue<Message> queue) {
		this.queue = queue;
	}
	
	
}
