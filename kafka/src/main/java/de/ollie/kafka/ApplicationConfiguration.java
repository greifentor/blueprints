package de.ollie.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
@Getter
public class ApplicationConfiguration {

	@Value(value = "${spring.kafka.bootstrap-servers:localhost:9092}")
	private String bootstrapAddress;

	@Value(value = "${spring.kafka.group-id:ollie-kafka-test}")
	private String groupId;

	@Value(value = "${spring.kafka.topic-name:ollie-kafka-test}")
	private String topicName;

}
