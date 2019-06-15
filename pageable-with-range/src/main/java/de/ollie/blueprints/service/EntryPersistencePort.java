package de.ollie.blueprints.service;

import java.time.LocalDate;
import java.util.List;

public interface EntryPersistencePort {

	List<EntrySO> readEntries(LocalDate from, LocalDate until);

}