package de.ollie.blueprints;

import java.util.ArrayList;

import javax.inject.Inject;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import de.ollie.blueprints.core.model.Company;
import de.ollie.blueprints.core.model.Employee;
import de.ollie.blueprints.core.service.CompanyService;

@SpringBootApplication
@ComponentScan("de.ollie")
public class Application implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Inject
//	private CompanyDBORepository repository;

	@Inject
	private CompanyService service;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Company company = new Company(null, "Raffke & Co.", new ArrayList<>());
		company.getEmployees().add(new Employee(null, "Depp Jones"));
		company = service.save(company);
		System.out.println("#1: " + company);
		company.getEmployees().add(new Employee(null, "Don Johnson"));
		company = service.save(company);
		System.out.println("#2: " + company);
		Employee employee = company.getEmployees().get(0);
		company.getEmployees().remove(employee);
		company = service.save(company);
		System.out.println("#3: " + company);
	}

}