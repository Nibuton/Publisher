package com.nibuton.intech.publisher.rest;

import org.apache.http.HttpResponse;

public interface Sender<T> {
	void send(T msg);
}
