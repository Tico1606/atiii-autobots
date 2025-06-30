package com.autobots.automanager.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Address extends RepresentationModel<Address> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String state;

  @Column(nullable = false)
  private String city;

  @Column(nullable = false)
  private String district;

  @Column(nullable = false)
  private String street;

  @Column(nullable = false)
  private String number;

  @Column(nullable = false)
  private String postalCode;

  @Column
  private String additionalInfo;
}
