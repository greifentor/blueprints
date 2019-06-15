package de.ollie.blueprints.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntryService {

	@Autowired
	private EntryPersistencePort entryPersistencePort;

	public List<EntrySO> readEntries(LocalDate from, LocalDate until) {
		return this.entryPersistencePort.readEntries(from, until);
	}

}