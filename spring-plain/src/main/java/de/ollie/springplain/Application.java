package de.ollie.springplain;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("de.ollie.springplain")
public class Application {

	public static void main(String[] args) throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
		AService aService = context.getBean(AService.class);
		aService.bla();
	}

}