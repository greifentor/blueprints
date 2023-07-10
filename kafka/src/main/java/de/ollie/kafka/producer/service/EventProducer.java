package de.ollie.kafka.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import de.ollie.kafka.ApplicationConfiguration;

@Component
public class EventProducer {

	@Autowired
	private ApplicationConfiguration appConfiguration;
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String msg) {
		kafkaTemplate.send(appConfiguration.getTopicName(), msg);
	}

}
