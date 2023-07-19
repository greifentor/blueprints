package de.ollie.blueprints.core.service.port;

import java.util.Optional;

import de.ollie.blueprints.core.model.Company;

public interface CompanyPersistencePort {

	Optional<Company> findById(Long id);

	public Company save(Company model);

}
