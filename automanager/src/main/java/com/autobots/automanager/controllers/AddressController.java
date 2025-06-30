package com.autobots.automanager.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entities.Address;
import com.autobots.automanager.repositories.AddressRepository;
import com.autobots.automanager.repositories.UserRepository;
import com.autobots.automanager.services.AddLinkAddress;
import com.autobots.automanager.services.UpdateAddress;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Address", description = "CRUD operations for addresses")
public class AddressController {

  @Autowired
  private AddressRepository addressRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AddLinkAddress addLinkService;

  @Autowired
  private UpdateAddress updateService;

  @PostMapping("/address/register")
  @Operation(summary = "Register a new address")
  public ResponseEntity<?> registerAddress(@RequestBody Address address) {
    if (addressRepository.findById(address.getId()).isPresent()) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    addressRepository.save(address);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/addresses")
  @Operation(summary = "Get all addresses")
  public ResponseEntity<List<Address>> getAddresses() {
    List<Address> addresses = addressRepository.findAll();
    if (addresses.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    addLinkService.addLink(new HashSet<>(addresses));
    return new ResponseEntity<>(addresses, HttpStatus.OK);
  }

  @GetMapping("/address/{id}")
  @Operation(summary = "Get an address by ID")
  public ResponseEntity<Address> getAddress(@PathVariable long id) {
    Optional<Address> address = addressRepository.findById(id);
    if (address.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    addLinkService.addLink(address.get());
    return new ResponseEntity<>(address.get(), HttpStatus.OK);
  }

  @PutMapping("/address/update")
  @Operation(summary = "Update an address")
  public ResponseEntity<?> updateAddress(@RequestBody Address updated) {
    Optional<Address> existing = addressRepository.findById(updated.getId());
    if (existing.isPresent()) {
      updateService.update(existing.get(), updated);
      addressRepository.save(existing.get());
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/address/delete")
  @Operation(summary = "Delete an address")
  public ResponseEntity<?> deleteAddress(@RequestBody Address toDelete) {
    Optional<Address> address = addressRepository.findById(toDelete.getId());
    if (address.isPresent()) {
      userRepository.findByAddress(address.get()).ifPresent(user -> {
        user.setAddress(null);
        userRepository.save(user);
      });
      addressRepository.delete(address.get());
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
