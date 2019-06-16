package de.ollie.blueprints.persistence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import de.ollie.blueprints.persistence.model.EntryModel;
import de.ollie.blueprints.persistence.repository.EntryRepository;
import de.ollie.blueprints.service.EntryPersistencePort;
import de.ollie.blueprints.service.EntrySO;

@Component
public class EntryPersistencePortImpl implements EntryPersistencePort {

	@Autowired
	private EntryRepository entryRepository;

	@Override
	public List<EntrySO> readEntries(LocalDate from, LocalDate until) {
		Pageable pageable = PageRequest.of(0, 5);
		Page<EntryModel> page = this.entryRepository
				.findAllByChangedOnDateGreaterThanEqualAndChangedOnDateLessThanEqual(from, until, pageable);
		List<EntrySO> l = new ArrayList<>(page.getSize());
		page.forEach(em -> {
			l.add(new EntrySO().setChangedOnDate(em.getChangedOnDate()).setDescription(em.getDescription())
					.setId(em.getId())); // (1)
		});
		return l;
	}

}

//(1) A converter class should be used here :o)