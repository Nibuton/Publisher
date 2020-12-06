package com.nibuton.intech.publisher.generator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.BlockingQueue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import com.nibuton.intech.publisher.entity.Message;

class MessageGeneratorTest {
	
	
	@Mock
	static private BlockingQueue<Message> queue;
	
	static private Generator<Message> generator;
	
	static private String[] actions;
	
	static private Message msg1;
	static private Message msg2;
	
	@BeforeAll
	static void setup() {
		generator = new MessageGenerator(queue);
		actions = new String[] {"PURCHASE","SUBSCRIPTION"};
		ReflectionTestUtils.setField(generator, "actions", actions);
		msg1 = generator.generate();
		msg2 = generator.generate();
		System.out.println(msg1);
		System.out.println(msg2);
	}
	
	@Test
	void foo() {
		System.out.println(msg1);
		msg1.getTimestamp();
		assertTrue(true);
	}
	
	@Test
	void testMessageIsGenerated() {
		assertNotNull(msg1);
		assertNotNull(msg2);
		assertTrue(msg1 instanceof Message);
		assertTrue(msg2 instanceof Message);
	}
	
	@Test
	void testMsisdnGeneratedCorrectly() {
		int msisdn1 = msg1.getMsisdn();
		int msisdn2 = msg2.getMsisdn();
		assertTrue(msisdn1  > 0 && msisdn1 <= Integer.MAX_VALUE);
		assertTrue(msisdn1  != msisdn2);
	}
	
	@Test
	void testTimestampGeneratedCorrectly() {
		Long timestamp1 = msg1.getTimestamp();
		assertNotNull(timestamp1);
		assertTrue(timestamp1 < Long.MAX_VALUE && timestamp1 > 0);
	}
	
	@Test
	void testActionGeneratedCorrectly() {
		Boolean result = false;
		String action1 = msg1.getAction();
		assertNotNull(action1);
		for (int i = 0; i < actions.length; i++) {
			if(action1.equals(actions[i])) {
				result = true; 
				break;
			}
		}
		assertTrue(result);
	}
	
	@Test
	void testIdGeneratedCorrectly() {
		int id1 = msg1.getId();
		int id2 = msg2.getId();
		assertTrue(id2 == (id1 + 1));
		assertTrue(msg1.getInstanceCounter() == msg2.getInstanceCounter());
	} 
	
}
