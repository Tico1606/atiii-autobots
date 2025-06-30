package com.autobots.automanager.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.autobots.automanager.entities.Phone;

@Component
public class UpdatePhone {
  @Autowired
  private VerifyStringNull verifyString;

  public void update(Phone phone, Phone updated) {
    if (updated != null) {
      if (!verifyString.verify(updated.getAreaCode())) {
        phone.setAreaCode(updated.getAreaCode());
      }
      if (!verifyString.verify(updated.getNumber())) {
        phone.setNumber(updated.getNumber());
      }
    }
  }

  public void update(Set<Phone> phones, Set<Phone> updates) {
    for (Phone updated : updates) {
      for (Phone phone : phones) {
        if (updated.getId() != null && updated.getId().equals(phone.getId())) {
          update(phone, updated);
        }
      }
    }
  }
}
