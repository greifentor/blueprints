package de.ollie.blueprints.persistence.mapper;

import org.mapstruct.Mapper;

import de.ollie.blueprints.core.model.Company;
import de.ollie.blueprints.persistence.entity.CompanyDBO;

@Mapper(componentModel = "spring")
public interface CompanyDBOMapper {

	Company toModel(CompanyDBO dbo);

	CompanyDBO toDBO(Company model);

}

