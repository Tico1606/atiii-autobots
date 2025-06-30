package com.autobots.automanager.controllers;

import com.autobots.automanager.entities.Company;
import com.autobots.automanager.entities.Services;
import com.autobots.automanager.repositories.CompanyRepository;
import com.autobots.automanager.repositories.ServiceRepository;
import com.autobots.automanager.services.AddLinkServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/service")
public class ServiceController {

  @Autowired
  private ServiceRepository serviceRepository;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private AddLinkServices addLink;

  @PostMapping("/create")
  public ResponseEntity<?> createService(@RequestBody Services service) {
    if (service.getCompany() == null || service.getCompany().getId() == null) {
      return ResponseEntity.badRequest().body("Company is required");
    }

    Optional<Company> companyOptional = companyRepository.findById(service.getCompany().getId());
    if (companyOptional.isEmpty()) {
      return ResponseEntity.badRequest().body("Company not found");
    }

    service.setCompany(companyOptional.get());

    Services newService = serviceRepository.save(service);
    addLink.addLink(newService);
    return ResponseEntity.ok(newService);
  }

  @GetMapping("/list")
  public ResponseEntity<List<Services>> listServices() {
    List<Services> services = serviceRepository.findAll();
    if (services.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    addLink.addLink(new HashSet<>(services));
    return ResponseEntity.ok(services);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Services> getService(@PathVariable Long id) {
    Optional<Services> service = serviceRepository.findById(id);
    if (service.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    addLink.addLink(service.get());
    return ResponseEntity.ok(service.get());
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<Services> updateService(@PathVariable Long id, @RequestBody Services updatedData) {
    Optional<Services> serviceOptional = serviceRepository.findById(id);
    if (serviceOptional.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Services service = serviceOptional.get();
    service.setName(updatedData.getName());
    service.setDescription(updatedData.getDescription());
    service.setPrice(updatedData.getPrice());
    Services updatedService = serviceRepository.save(service);

    updatedService.getCompany().getLegalName();

    addLink.addLink(updatedService);
    return ResponseEntity.ok(updatedService);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteService(@PathVariable Long id) {
    Optional<Services> service = serviceRepository.findById(id);
    if (service.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    serviceRepository.delete(service.get());
    return ResponseEntity.noContent().build();
  }
}
