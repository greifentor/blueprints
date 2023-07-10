package de.ollie.springplain;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AService {

	@Autowired
	private AnotherService anthAnotherService;

	@PostConstruct
	void postConstruct() {
		System.out.println("post constructed ...");
	}

	public void bla() {
		System.out.println("bla");
		anthAnotherService.anotherBla();
	}

}
