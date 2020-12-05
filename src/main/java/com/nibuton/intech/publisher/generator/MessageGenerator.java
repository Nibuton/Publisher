package com.nibuton.intech.publisher.generator;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nibuton.intech.publisher.entity.Message;

@Component
public class MessageGenerator implements Generator<Message>, Runnable{
	
	private String[] actions;
	
	private BlockingQueue<Message> queue;
	
	@Autowired
	public MessageGenerator(BlockingQueue<Message> queue) {
		this.queue = queue;
	}

	@Override
	public Message generate() {
		Message msg = new Message();
		msg.setMsisdn(ThreadLocalRandom.current().nextInt(100000000));
		msg.setTimestamp(System.currentTimeMillis());
		msg.setAction(actions[ThreadLocalRandom.current().nextInt(2)]);
		return msg;
	}

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
