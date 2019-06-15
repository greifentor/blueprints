package de.ollie.blueprints.controller.model;

import java.time.LocalDate;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class EntryDTO {

	private long id;
	private String description;
	private LocalDate changedOnDate;

}