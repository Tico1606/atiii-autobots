package com.autobots.automanager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.autobots.automanager.entities.Vehicle;

@Component
public class UpdateVehicle {
  @Autowired
  private VerifyStringNull verifyString;

  public void update(Vehicle vehicle, Vehicle updated) {
    if (updated != null) {
      if (!verifyString.verify(updated.getType().toString())) {
        vehicle.setType(updated.getType());
      }
      if (!verifyString.verify(updated.getModel())) {
        vehicle.setModel(updated.getModel());
      }
      if (!verifyString.verify(updated.getPlate())) {
        vehicle.setPlate(updated.getPlate());
      }
    }
  }

  public void update(List<Vehicle> vehicles, List<Vehicle> updates) {
    for (Vehicle updated : updates) {
      for (Vehicle vehicle : vehicles) {
        if (updated.getId() != null && updated.getId().equals(vehicle.getId())) {
          update(vehicle, updated);
        }
      }
    }
  }
}
