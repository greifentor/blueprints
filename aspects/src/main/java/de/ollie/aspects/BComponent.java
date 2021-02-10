package de.ollie.aspects;

import org.springframework.stereotype.Component;

@Component
public class BComponent {

	@LogExecutionTime
	public void serve() throws InterruptedException {
		Thread.sleep(2000);
		System.out.println("served");
	}

}