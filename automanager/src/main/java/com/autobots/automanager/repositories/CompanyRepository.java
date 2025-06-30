package com.autobots.automanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.autobots.automanager.entities.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
