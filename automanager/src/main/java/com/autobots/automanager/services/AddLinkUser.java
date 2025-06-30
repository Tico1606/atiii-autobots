package com.autobots.automanager.services;

import java.util.Set;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import com.autobots.automanager.controllers.UserController;
import com.autobots.automanager.entities.User;

@Service
public class AddLinkUser implements LinkAdder<User> {

  @Override
  public void addLink(Set<User> users) {
    for (User user : users) {
      addLink(user);
    }
  }

  @Override
  public void addLink(User user) {
    

    Link allLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(UserController.class).listUsers())
        .withRel("get-all-users");

    Link createLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(UserController.class).createUser(null))
        .withRel("create-user");

    Link deleteLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(UserController.class).deleteUser(user.getId()))
        .withRel("delete-user");

    user.add(allLink, createLink, deleteLink);
  }
}
