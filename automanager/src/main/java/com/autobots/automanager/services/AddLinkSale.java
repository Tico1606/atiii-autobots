package com.autobots.automanager.services;

import java.util.Set;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import com.autobots.automanager.controllers.SaleController;
import com.autobots.automanager.entities.Sale;

@Service
public class AddLinkSale implements LinkAdder<Sale> {

  @Override
  public void addLink(Set<Sale> sales) {
    for (Sale sale : sales) {
      addLink(sale);
    }
  }

  @Override
  public void addLink(Sale sale) {
    Link selfLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(SaleController.class).getSale(sale.getId()))
        .withRel("get-sale");

    sale.add(selfLink);
  }
}
