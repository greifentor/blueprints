package de.ollie.blueprints.persistence.adapter;

import java.util.Optional;

import javax.inject.Named;

import de.ollie.blueprints.core.model.Company;
import de.ollie.blueprints.core.service.port.CompanyPersistencePort;
import de.ollie.blueprints.persistence.mapper.CompanyDBOMapper;
import de.ollie.blueprints.persistence.repository.CompanyDBORepository;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class CompanyJPAPersistenceAdapter implements CompanyPersistencePort {

	private final CompanyDBOMapper mapper;
	private final CompanyDBORepository repository;

	@Override
	public Optional<Company> findById(Long id) {
		return repository.findById(id).map(mapper::toModel);
	}

	@Override
	public Company save(Company model) {
		return mapper.toModel(repository.save(mapper.toDBO(model)));
	}

}
