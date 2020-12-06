package com.nibuton.intech.publisher.rest;

public interface Sender<T> {
	void send(T msg);
}
