package de.ollie.blueprints.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;

@AllArgsConstructor
@Data
@Generated
public class Book {

	private Long id;
	private String title;
	private Rack rack;

}