package de.ollie.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import tst.Displayer;

@SpringBootApplication
@ComponentScan("de.ollie")
public class Application {

	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
		try {
			final Displayer d = (Displayer) Class.forName("added.DisplayerImpl").newInstance();
			System.out.println("\n##### " + d.getStringToDisplay());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}