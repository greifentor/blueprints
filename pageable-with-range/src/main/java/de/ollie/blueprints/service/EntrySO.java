package de.ollie.blueprints.service;

import java.time.LocalDate;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class EntrySO {

	private long id;
	private String description;
	private LocalDate changedOnDate;

}