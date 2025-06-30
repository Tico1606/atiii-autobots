package com.autobots.automanager.repositories;

import com.autobots.automanager.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
