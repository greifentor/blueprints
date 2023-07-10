package de.ollie.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import de.ollie.kafka.cli.CLIRunner;

@SpringBootApplication
@ComponentScan("de.ollie")
@EnableScheduling
public class Application implements ApplicationRunner {

	public static void main(String[] args) throws Exception {
		new SpringApplicationBuilder(Application.class).web(WebApplicationType.NONE).run(args);
	}

	@Autowired
	private CLIRunner cliRunner;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		cliRunner.run(args);
	}

}