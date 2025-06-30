package com.autobots.automanager.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entities.Services;

@Service
public class UpdateServices {
  @Autowired
  private VerifyStringNull verifyStringNull;
  @Autowired
  private VerifyDoubleNull verifyDoubleNull;

  public void update(Services service, Services update) {
    if (update != null) {
      if (!verifyStringNull.verify(update.getName())) {
        service.setName(update.getName());
      }
      if (!verifyDoubleNull.verify(update.getPrice())) {
        service.setPrice(update.getPrice());
      }
      if (!verifyStringNull.verify(update.getDescription())) {
        service.setDescription(update.getDescription());
      }
    }
  }

  public void update(List<Services> services, List<Services> updates) {
    for (Services update : updates) {
      for (Services service : services) {
        if (update.getId() != null && update.getId().equals(service.getId())) {
          update(service, update);
        }
      }
    }
  }
}
