package com.nibuton.intech.publisher.entity;

/**
 * Класс для объекта сообщения
 * @author nibuton
 */

public class Message {
	
	/**
	 * Счетчик количества созданных экземпляров. Используется для назначения id при генерации очередного 
	 * сообщения. Используется несколькими потоками.
	 */
	private static volatile int instanceCounter = 0;
	
	private int id;
	private int msisdn;
	private Long timestamp;
	private String action;
	
	{
		this.id = instanceCounter;
		instanceCounter+=1;
	}

	public Message() {
	}

	public Message(int msisdn, Long timestamp, String action) {
		this.msisdn = msisdn;
		this.timestamp = timestamp;
		this.action= action;
	}


	public int getMsisdn() {
		return msisdn;
	}


	public void setMsisdn(int msisdn) {
		this.msisdn = msisdn;
	}


	public Long getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public int getInstanceCounter() {
		return instanceCounter;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + "msisdn=" + msisdn + ", timestamp=" + timestamp + ", action=" + action + "]";
	}
	
	

}
