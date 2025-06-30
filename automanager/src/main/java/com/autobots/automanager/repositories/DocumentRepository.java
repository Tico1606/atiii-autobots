package com.autobots.automanager.repositories;

import com.autobots.automanager.entities.Document;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
  Optional<Document> findByNumber(String number);
}
