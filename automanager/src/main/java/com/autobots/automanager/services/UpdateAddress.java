package com.autobots.automanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entities.Address;

@Service
public class UpdateAddress {
  @Autowired
  private VerifyStringNull verifyStringNull;

  public void update(Address address, Address update) {
    if (update != null) {
      if (!verifyStringNull.verify(update.getState())) {
        address.setState(update.getState());
      }
      if (!verifyStringNull.verify(update.getCity())) {
        address.setCity(update.getCity());
      }
      if (!verifyStringNull.verify(update.getDistrict())) {
        address.setDistrict(update.getDistrict());
      }
      if (!verifyStringNull.verify(update.getStreet())) {
        address.setStreet(update.getStreet());
      }
      if (!verifyStringNull.verify(update.getNumber())) {
        address.setNumber(update.getNumber());
      }
      if (!verifyStringNull.verify(update.getAdditionalInfo())) {
        address.setAdditionalInfo(update.getAdditionalInfo());
      }
    }
  }
}
