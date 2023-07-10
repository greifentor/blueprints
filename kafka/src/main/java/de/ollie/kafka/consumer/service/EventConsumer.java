package de.ollie.kafka.consumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EventConsumer {

	@KafkaListener(topics = "${spring.kafka.topic-name:ollie-kafka-test}", groupId = "${spring.kafka.group-id:ollie-kafka-test}")
	public void listenGroupFoo(String message) {
		System.out.println("received: " + message);
	}

}
