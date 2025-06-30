package com.autobots.automanager.repositories;

import com.autobots.automanager.entities.Address;
import com.autobots.automanager.entities.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByAddress(Address address);
}
