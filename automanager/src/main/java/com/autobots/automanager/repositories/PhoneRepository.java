
package com.autobots.automanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autobots.automanager.entities.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
