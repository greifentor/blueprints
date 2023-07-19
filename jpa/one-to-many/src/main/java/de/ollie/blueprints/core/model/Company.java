package de.ollie.blueprints.core.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Company {

	private Long id;
	private String name;
	private List<Employee> employees;

}
