package com.autobots.automanager.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entities.Document;
import com.autobots.automanager.repositories.DocumentRepository;
import com.autobots.automanager.services.AddLinkDocument;
import com.autobots.automanager.services.UpdateDocument;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Document", description = "CRUD operations for documents")
public class DocumentController {

  @Autowired
  private DocumentRepository repository;

  @Autowired
  private AddLinkDocument addLinkService;

  @Autowired
  private UpdateDocument updateService;

  @PostMapping("/document/register")
  @Operation(summary = "Register a document")
  public ResponseEntity<?> registerDocument(@RequestBody Document document) {
    if (repository.findByNumber(document.getNumber()).isPresent()) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    repository.save(document);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/documents")
  @Operation(summary = "Get all documents")
  public ResponseEntity<List<Document>> getDocuments() {
    List<Document> documents = repository.findAll();
    if (documents.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    addLinkService.addLink(new HashSet<>(documents));
    return new ResponseEntity<>(documents, HttpStatus.OK);
  }

  @GetMapping("/document/{id}")
  @Operation(summary = "Get document by ID")
  public ResponseEntity<Document> getDocument(@PathVariable long id) {
    Optional<Document> document = repository.findById(id);
    if (document.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    addLinkService.addLink(document.get());
    return new ResponseEntity<>(document.get(), HttpStatus.OK);
  }

  @PutMapping("/document/update")
  @Operation(summary = "Update a document")
  public ResponseEntity<?> updateDocument(@RequestBody Document updated) {
    Optional<Document> existing = repository.findById(updated.getId());
    if (existing.isPresent()) {
      updateService.update(existing.get(), updated);
      repository.save(existing.get());
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/document/delete")
  @Operation(summary = "Delete a document")
  public ResponseEntity<?> deleteDocument(@RequestBody Document toDelete) {
    Optional<Document> document = repository.findById(toDelete.getId());
    if (document.isPresent()) {
      repository.delete(document.get());
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
