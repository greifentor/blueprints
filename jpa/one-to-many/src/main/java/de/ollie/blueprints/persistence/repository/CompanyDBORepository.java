package de.ollie.blueprints.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.ollie.blueprints.persistence.entity.CompanyDBO;

@Repository
public interface CompanyDBORepository extends JpaRepository<CompanyDBO, Long> {
}
