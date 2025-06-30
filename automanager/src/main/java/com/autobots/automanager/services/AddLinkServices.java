package com.autobots.automanager.services;

import java.util.Set;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import com.autobots.automanager.controllers.ServiceController;
import com.autobots.automanager.entities.Services;

@Service
public class AddLinkServices implements LinkAdder<Services> {

  @Override
  public void addLink(Set<Services> services) {
    for (Services service : services) {
      addLink(service);
    }
  }

  @Override
  public void addLink(Services service) {
    Link selfLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(ServiceController.class).getService(service.getId()))
        .withRel("get-service");

    Link allLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(ServiceController.class).listServices())
        .withRel("get-all-services");

    Link createLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(ServiceController.class).createService(null))
        .withRel("create-service");

    Link deleteLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(ServiceController.class).deleteService(service.getId()))
        .withRel("delete-service");

    service.add(selfLink, allLink, createLink, deleteLink);
  }
}
