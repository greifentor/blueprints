package de.ollie.blueprints.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Rack {

	private Long id;
	private String name;
	private Room room;

}