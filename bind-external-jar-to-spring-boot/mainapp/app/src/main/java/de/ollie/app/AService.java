package de.ollie.app;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class AService {

	@PostConstruct
	void bla() {
		System.out.println("\nService started.\n");
	}

}
