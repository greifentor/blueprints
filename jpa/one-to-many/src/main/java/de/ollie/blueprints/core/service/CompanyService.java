package de.ollie.blueprints.core.service;

import java.util.Optional;

import de.ollie.blueprints.core.model.Company;

public interface CompanyService {

	Optional<Company> findById(Long id);

	Company save(Company model);

}
