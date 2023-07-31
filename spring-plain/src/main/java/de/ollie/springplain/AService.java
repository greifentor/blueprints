package de.ollie.springplain;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AService {

	@Autowired
	private AConfiguration aConfiguration;
	@Autowired
	private AnotherService anthAnotherService;

	@PostConstruct
	void postConstruct() {
		System.out.println("post constructed ...");
		System.out.println("conf (Property): " + aConfiguration.getAValue());
		System.out.println("conf (Environment): " + aConfiguration.getAnEnvironmentValue());
		System.out.println("conf (Environment by Property): " + aConfiguration.getAnEnvironmentValueByProperty());
		System.out.println("System.getenv(): " + System.getenv("TEST_VALUE"));
	}

	public void bla() {
		System.out.println("bla");
		anthAnotherService.anotherBla();
	}

}
