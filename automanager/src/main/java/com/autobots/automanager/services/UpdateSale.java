package com.autobots.automanager.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.autobots.automanager.entities.Sale;

@Component
public class UpdateSale {
  @Autowired
  private VerifyStringNull verifyString;

  public void update(Sale sale, Sale updated) {
    if (updated != null) {
      if (!verifyString.verify(updated.getIdentification())) {
        sale.setIdentification(updated.getIdentification());
      }
      if (updated.getRegistrationDate() != null) {
        sale.setRegistrationDate(updated.getRegistrationDate());
      }
    }
  }

  public void update(Set<Sale> sales, Set<Sale> updates) {
    for (Sale updated : updates) {
      for (Sale sale : sales) {
        if (updated.getId() != null && updated.getId().equals(sale.getId())) {
          update(sale, updated);
        }
      }
    }
  }
}
