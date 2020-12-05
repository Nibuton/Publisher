package com.nibuton.intech.publisher.rest;

import java.util.concurrent.BlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;

import com.nibuton.intech.publisher.entity.Message;

public class MessageSendTask implements Runnable{
	
	private Sender<Message> sender;
	private BlockingQueue<Message> queue;
	
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
				sender.send(msg);
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}

}
