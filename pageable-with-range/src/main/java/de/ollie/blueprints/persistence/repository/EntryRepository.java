package de.ollie.blueprints.persistence.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import de.ollie.blueprints.persistence.model.EntryModel;

@Repository
public interface EntryRepository extends PagingAndSortingRepository<EntryModel, Long> {

	Page<EntryModel> findAllByChangedOnDateGreaterThanEqualAndChangedOnDateLessThanEqual(LocalDate from,
			LocalDate until, Pageable pageable);

}