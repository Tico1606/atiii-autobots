package com.autobots.automanager.services;

import java.util.Set;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import com.autobots.automanager.controllers.AddressController;
import com.autobots.automanager.entities.Address;

@Service
public class AddLinkAddress implements LinkAdder<Address> {

  @Override
  public void addLink(Set<Address> addresses) {
    for (Address address : addresses) {
      addLink(address);
    }
  }

  @Override
  public void addLink(Address address) {
    Link selfLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder
            .methodOn(AddressController.class)
            .getAddress(address.getId()))
        .withRel("get-address");

    Link allLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder
            .methodOn(AddressController.class)
            .getAddresses())
        .withRel("get-all-addresses");

    Link createLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder
            .methodOn(AddressController.class)
            .registerAddress(null))
        .withRel("create-address");

    Link updateLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder
            .methodOn(AddressController.class)
            .updateAddress(null))
        .withRel("update-address");

    Link deleteLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder
            .methodOn(AddressController.class)
            .deleteAddress(null))
        .withRel("delete-address");

    address.add(selfLink, allLink, createLink, updateLink, deleteLink);
  }
}
