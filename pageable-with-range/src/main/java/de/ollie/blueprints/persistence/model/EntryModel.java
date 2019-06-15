package de.ollie.blueprints.persistence.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ENTRY")
public class EntryModel {

	@Id
	@Column(name = "ID")
	private long id;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "CHANGED_ON_DATE")
	private LocalDate changedOnDate;

}