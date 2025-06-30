package com.autobots.automanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.autobots.automanager.entities.Services;

public interface ServiceRepository extends JpaRepository<Services, Long> {
}
