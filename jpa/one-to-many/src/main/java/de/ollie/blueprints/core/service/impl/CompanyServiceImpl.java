package de.ollie.blueprints.core.service.impl;

import java.util.Optional;

import javax.inject.Named;

import de.ollie.blueprints.core.model.Company;
import de.ollie.blueprints.core.service.CompanyService;
import de.ollie.blueprints.core.service.port.CompanyPersistencePort;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

	private final CompanyPersistencePort persistencePort;

	@Override
	public Optional<Company> findById(Long id) {
		return persistencePort.findById(id);
	}

	@Override
	public Company save(Company model) {
		return persistencePort.save(model);
	}

}
