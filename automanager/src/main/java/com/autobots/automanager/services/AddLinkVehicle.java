package com.autobots.automanager.services;

import java.util.Set;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import com.autobots.automanager.controllers.VehicleController;
import com.autobots.automanager.entities.Vehicle;

@Service
public class AddLinkVehicle implements LinkAdder<Vehicle> {

  @Override
  public void addLink(Set<Vehicle> vehicles) {
    for (Vehicle vehicle : vehicles) {
      addLink(vehicle);
    }
  }

  @Override
  public void addLink(Vehicle vehicle) {
    Link selfLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(VehicleController.class).getVehicle(vehicle.getId()))
        .withRel("get-vehicle");

    Link allLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(VehicleController.class).listVehicles())
        .withRel("get-all-vehicles");

    Link createLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(VehicleController.class).createVehicle(null))
        .withRel("create-vehicle");

    Link deleteLink = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(VehicleController.class).deleteVehicle(vehicle.getId()))
        .withRel("delete-vehicle");

    vehicle.add(selfLink, allLink, createLink, deleteLink);
  }
}
