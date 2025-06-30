package com.autobots.automanager.services;

import java.util.Set;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import com.autobots.automanager.controllers.MerchandiseController;
import com.autobots.automanager.entities.Merchandise;

@Service
public class AddLinkMerchandise implements LinkAdder<Merchandise> {

  @Override
  public void addLink(Set<Merchandise> merchandises) {
    for (Merchandise merchandise : merchandises) {
      addLink(merchandise);
    }
  }

  @Override
  public void addLink(Merchandise merchandise) {
    Link selfLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(MerchandiseController.class).getMerchandise(merchandise.getId()))
        .withRel("get-merchandise");

    Link allLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(MerchandiseController.class).listMerchandise())
        .withRel("get-all-merchandises");

    Link createLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(MerchandiseController.class).createMerchandise(null))
        .withRel("create-merchandise");

    Link deleteLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(MerchandiseController.class).deleteMerchandise(null))
        .withRel("delete-merchandise");

    merchandise.add(selfLink, allLink, createLink, deleteLink);
  }
}
