package com.autobots.automanager.services;

import java.util.Set;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import com.autobots.automanager.controllers.CompanyController;
import com.autobots.automanager.entities.Company;

@Service
public class AddLinkCompany implements LinkAdder<Company> {

  @Override
  public void addLink(Set<Company> companies) {
    for (Company company : companies) {
      addLink(company);
    }
  }

  @Override
  public void addLink(Company company) {
    Link selfLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(CompanyController.class).getCompany(company.getId()))
        .withRel("get-company");

    Link allLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(CompanyController.class).getCompanies())
        .withRel("get-all-companies");

    Link createLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(CompanyController.class).registerCompany(null))
        .withRel("create-company");

    Link updateLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(CompanyController.class).updateCompany(null))
        .withRel("update-company");

    Link deleteLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(CompanyController.class).deleteCompany(null))
        .withRel("delete-company");

    company.add(selfLink, allLink, createLink, updateLink, deleteLink);
  }
}
