package com.autobots.automanager.controllers;

import com.autobots.automanager.entities.Company;
import com.autobots.automanager.entities.Vehicle;
import com.autobots.automanager.repositories.CompanyRepository;
import com.autobots.automanager.repositories.VehicleRepository;
import com.autobots.automanager.services.AddLinkVehicle;
import com.autobots.automanager.services.UpdateVehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

  @Autowired
  private VehicleRepository vehicleRepository;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private AddLinkVehicle addLink;

  @Autowired
  private UpdateVehicle updateVehicle;

  @PostMapping("/create")
  public ResponseEntity<?> createVehicle(@RequestBody Vehicle vehicle) {
    if (vehicle.getCompany() == null || vehicle.getCompany().getId() == null) {
      return ResponseEntity.badRequest().body("Company is required");
    }

    Optional<Company> companyOptional = companyRepository.findById(vehicle.getCompany().getId());
    if (companyOptional.isEmpty()) {
      return ResponseEntity.badRequest().body("Company not found");
    }

    vehicle.setCompany(companyOptional.get());

    Vehicle newVehicle = vehicleRepository.save(vehicle);
    addLink.addLink(newVehicle);
    return ResponseEntity.ok(newVehicle);
  }

  @GetMapping("/list")
  public ResponseEntity<List<Vehicle>> listVehicles() {
    List<Vehicle> vehicles = vehicleRepository.findAll();
    if (vehicles.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    addLink.addLink(new HashSet<>(vehicles));
    return ResponseEntity.ok(vehicles);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Vehicle> getVehicle(@PathVariable Long id) {
    Optional<Vehicle> vehicle = vehicleRepository.findById(id);
    if (vehicle.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    addLink.addLink(vehicle.get());
    return ResponseEntity.ok(vehicle.get());
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id, @RequestBody Vehicle updatedData) {
    Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
    if (optionalVehicle.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Vehicle vehicle = optionalVehicle.get();
    updateVehicle.update(vehicle, updatedData);
    Vehicle updatedVehicle = vehicleRepository.save(vehicle);

    updatedVehicle.getCompany().getLegalName();

    addLink.addLink(updatedVehicle);
    return ResponseEntity.ok(updatedVehicle);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
    Optional<Vehicle> vehicle = vehicleRepository.findById(id);
    if (vehicle.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    vehicleRepository.delete(vehicle.get());
    return ResponseEntity.noContent().build();
  }
}
