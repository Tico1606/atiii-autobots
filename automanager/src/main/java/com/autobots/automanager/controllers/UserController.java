package com.autobots.automanager.controllers;

import com.autobots.automanager.entities.User;
import com.autobots.automanager.entities.Company;
import com.autobots.automanager.repositories.CompanyRepository;
import com.autobots.automanager.repositories.UserRepository;
import com.autobots.automanager.services.AddLinkUser;
import com.autobots.automanager.services.UpdateUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private AddLinkUser addLink;

  @Autowired
  private UpdateUser updateUser;

  @GetMapping("/list")
  public ResponseEntity<List<User>> listUsers() {
    List<User> users = userRepository.findAll();
    if (users.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    addLink.addLink(new HashSet<>(users));
    return ResponseEntity.ok(users);
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUser(@PathVariable Long id) {
    Optional<User> user = userRepository.findById(id);
    if (user.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    addLink.addLink(user.get());
    return ResponseEntity.ok(user.get());
  }

  @PostMapping("/create")
  public ResponseEntity<?> createUser(@RequestBody User user) {
    if (user.getCompany() == null || user.getCompany().getId() == null) {
      return ResponseEntity.badRequest().body("Company is required");
    }

    Optional<Company> empresaOptional = companyRepository.findById(user.getCompany().getId());
    if (empresaOptional.isEmpty()) {
      return ResponseEntity.badRequest().body("Company not found");
    }

    user.setCompany(empresaOptional.get());

    User newUser = userRepository.save(user);
    addLink.addLink(newUser);
    return ResponseEntity.ok(newUser);
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
    Optional<User> userOptional = userRepository.findById(id);
    if (userOptional.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    User user = userOptional.get();
    updateUser.update(user, updatedUser);
    User savedUser = userRepository.save(user);

    savedUser.getCompany().getLegalName();

    addLink.addLink(savedUser);
    return ResponseEntity.ok(savedUser);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    Optional<User> user = userRepository.findById(id);
    if (user.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    userRepository.delete(user.get());
    return ResponseEntity.noContent().build();
  }
}
