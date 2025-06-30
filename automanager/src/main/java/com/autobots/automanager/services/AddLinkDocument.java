package com.autobots.automanager.services;

import java.util.Set;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import com.autobots.automanager.controllers.DocumentController;
import com.autobots.automanager.entities.Document;

@Service
public class AddLinkDocument implements LinkAdder<Document> {

  @Override
  public void addLink(Set<Document> documents) {
    for (Document document : documents) {
      addLink(document);
    }
  }

  @Override
  public void addLink(Document document) {
    Link selfLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder
            .methodOn(DocumentController.class)
            .getDocument(document.getId()))
        .withRel("get-document");

    Link allLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder
            .methodOn(DocumentController.class)
            .getDocuments())
        .withRel("get-all-documents");

    Link createLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder
            .methodOn(DocumentController.class)
            .registerDocument(null))
        .withRel("create-document");

    Link updateLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder
            .methodOn(DocumentController.class)
            .updateDocument(null))
        .withRel("update-document");

    Link deleteLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder
            .methodOn(DocumentController.class)
            .deleteDocument(null))
        .withRel("delete-document");

    document.add(selfLink, allLink, createLink, updateLink, deleteLink);
  }
}
