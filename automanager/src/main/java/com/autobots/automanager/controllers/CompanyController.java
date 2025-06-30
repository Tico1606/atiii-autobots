package com.autobots.automanager.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entities.Company;
import com.autobots.automanager.repositories.CompanyRepository;
import com.autobots.automanager.services.AddLinkCompany;
import com.autobots.automanager.services.UpdateCompany;

@RestController
public class CompanyController {

  @Autowired
  private CompanyRepository repository;

  @Autowired
  private AddLinkCompany addLinkService;

  @Autowired
  private UpdateCompany updateService;

  @PostMapping("/company/register")
  public ResponseEntity<?> registerCompany(@RequestBody Company company) {
    repository.save(company);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/companies")
  public ResponseEntity<List<Company>> getCompanies() {
    List<Company> companies = repository.findAll();
    if (companies.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    addLinkService.addLink(new HashSet<>(companies));
    return new ResponseEntity<>(companies, HttpStatus.OK);
  }

  @GetMapping("/company/{id}")
  public ResponseEntity<Company> getCompany(@PathVariable long id) {
    Optional<Company> company = repository.findById(id);
    if (company.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    addLinkService.addLink(company.get());
    return new ResponseEntity<>(company.get(), HttpStatus.OK);
  }

  @PutMapping("/company/update")
  public ResponseEntity<?> updateCompany(@RequestBody Company updated) {
    Optional<Company> existing = repository.findById(updated.getId());
    if (existing.isPresent()) {
      updateService.update(existing.get(), updated);
      repository.save(existing.get());
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/company/delete")
  public ResponseEntity<?> deleteCompany(@RequestBody Company toDelete) {
    Optional<Company> company = repository.findById(toDelete.getId());
    if (company.isPresent()) {
      repository.delete(company.get());
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
