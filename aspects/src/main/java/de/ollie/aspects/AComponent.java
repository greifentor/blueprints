package de.ollie.aspects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AComponent {

	@Autowired
	private BComponent component;

	@Scheduled(fixedDelay = 1000)
	@LogExecutionTime
	public void scheduleFixedDelayTask() {
		System.out.println("scheduled");
		try {
			component.serve();
		} catch (Exception e) {
		}
	}

}