package de.ollie.springplain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
@Getter
public class AConfiguration {

	@Value("${a.value}")
	private String aValue;

	@Value("#{environment.TEST_VALUE}")
	private String anEnvironmentValue;

	@Value("${test.value}")
	private String anEnvironmentValueByProperty;

}
