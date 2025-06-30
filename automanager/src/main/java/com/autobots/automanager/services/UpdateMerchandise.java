package com.autobots.automanager.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entities.Merchandise;

@Service
public class UpdateMerchandise {
  @Autowired
  private VerifyStringNull verifyStringNull;
  @Autowired
  private VerifyDoubleNull verifyDoubleNull;

  public void update(Merchandise merchandise, Merchandise update) {
    if (update != null) {
      if (!verifyStringNull.verify(update.getName())) {
        merchandise.setName(update.getName());
      }
      if (!verifyDoubleNull.verify(update.getPrice())) {
        merchandise.setPrice(update.getPrice());
      }
      if (update.getRegistrationDate() != null) {
        merchandise.setRegistrationDate(update.getRegistrationDate());
      }
      if (update.getManufactureDate() != null) {
        merchandise.setManufactureDate(update.getManufactureDate());
      }
      if (update.getExpirationDate() != null) {
        merchandise.setExpirationDate(update.getExpirationDate());
      }
      if (update.getQuantity() >= 0) {
        merchandise.setQuantity(update.getQuantity());
      }
      if (!verifyStringNull.verify(update.getDescription())) {
        merchandise.setDescription(update.getDescription());
      }
    }
  }

  public void update(List<Merchandise> items, List<Merchandise> updates) {
    for (Merchandise update : updates) {
      for (Merchandise item : items) {
        if (update.getId() != null && update.getId().equals(item.getId())) {
          update(item, update);
        }
      }
    }
  }
}
