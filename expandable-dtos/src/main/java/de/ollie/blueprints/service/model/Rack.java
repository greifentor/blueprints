package de.ollie.blueprints.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;

@AllArgsConstructor
@Data
@Generated
public class Rack {

	private Long id;
	private String name;
	private Room room;

}