package com.autobots.automanager.controllers;

import com.autobots.automanager.entities.Phone;
import com.autobots.automanager.repositories.PhoneRepository;
import com.autobots.automanager.services.AddLinkPhone;
import com.autobots.automanager.services.UpdatePhone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Phone", description = "Phone CRUD operations")
public class PhoneController {

  @Autowired
  private PhoneRepository repository;

  @Autowired
  private AddLinkPhone addLink;

  @Autowired
  private UpdatePhone updatePhone;

  @PostMapping("/phone/register")
  @Operation(summary = "Register phone", description = "Registers a new phone")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Phone successfully registered"),
      @ApiResponse(responseCode = "409", description = "Phone already registered")
  })
  public ResponseEntity<?> registerPhone(@RequestBody Phone phone) {
    if (phone.getAreaCode() == null || phone.getNumber() == null) {
      return new ResponseEntity<>("Area code and number are required", HttpStatus.BAD_REQUEST);
    }

    repository.save(phone);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/phones")
  @Operation(summary = "Get all phones", description = "Returns a list of all registered phones")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Phones found", content = @Content(schema = @Schema(implementation = List.class))),
      @ApiResponse(responseCode = "404", description = "No phones found")
  })
  public ResponseEntity<List<Phone>> getPhones() {
    List<Phone> phones = repository.findAll();
    if (phones.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      addLink.addLink(new HashSet<>(phones));
      return new ResponseEntity<>(phones, HttpStatus.OK);
    }
  }

  @GetMapping("/phone/{id}")
  @Operation(summary = "Get phone", description = "Returns a specific phone by ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Phone found", content = @Content(schema = @Schema(implementation = Phone.class))),
      @ApiResponse(responseCode = "404", description = "Phone not found")
  })
  public ResponseEntity<Phone> getPhone(@PathVariable long id) {
    Optional<Phone> phone = repository.findById(id);
    if (phone.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      addLink.addLink(phone.get());
      return ResponseEntity.ok(phone.get());
    }
  }

  @PutMapping("/phone/update")
  @Operation(summary = "Update phone", description = "Updates an existing phone")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Phone updated successfully"),
      @ApiResponse(responseCode = "404", description = "Phone not found")
  })
  public ResponseEntity<?> updatePhone(@RequestBody Phone updatedPhone) {
    Optional<Phone> phone = repository.findById(updatedPhone.getId());
    if (phone.isPresent()) {
      updatePhone.update(phone.get(), updatedPhone);
      repository.save(phone.get());
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/phone/delete")
  @Operation(summary = "Delete phone", description = "Deletes an existing phone")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Phone deleted successfully"),
      @ApiResponse(responseCode = "404", description = "Phone not found")
  })
  public ResponseEntity<?> deletePhone(@RequestBody Phone toDelete) {
    Optional<Phone> phone = repository.findById(toDelete.getId());
    if (phone.isPresent()) {
      repository.delete(phone.get());
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
