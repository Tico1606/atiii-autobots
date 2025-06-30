package com.autobots.automanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.autobots.automanager.entities.Merchandise;

public interface MerchandiseRepository extends JpaRepository<Merchandise, Long> {
}
