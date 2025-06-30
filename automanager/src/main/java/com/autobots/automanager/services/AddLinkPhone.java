package com.autobots.automanager.services;

import java.util.Set;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import com.autobots.automanager.controllers.PhoneController;
import com.autobots.automanager.entities.Phone;

@Service
public class AddLinkPhone implements LinkAdder<Phone> {

  @Override
  public void addLink(Set<Phone> phones) {
    for (Phone phone : phones) {
      addLink(phone);
    }
  }

  @Override
  public void addLink(Phone phone) {
    Link selfLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(PhoneController.class).getPhone(phone.getId()))
        .withRel("get-phone");

    Link allLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(PhoneController.class).getPhones())
        .withRel("get-all-phones");

    Link createLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(PhoneController.class).registerPhone(null))
        .withRel("create-phone");

    Link updateLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(PhoneController.class).updatePhone(null))
        .withRel("update-phone");

    Link deleteLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(PhoneController.class).deletePhone(null))
        .withRel("delete-phone");

    phone.add(selfLink, allLink, createLink, updateLink, deleteLink);
  }
}
