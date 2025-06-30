package com.autobots.automanager.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.springframework.hateoas.RepresentationModel;

import com.autobots.automanager.enums.VehicleType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(exclude = { "owner", "sales" }, callSuper = false)
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle extends RepresentationModel<Vehicle> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private VehicleType type;

  @Column(nullable = false)
  private String model;

  @Column(nullable = false)
  private String plate;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "company_id", nullable = false)
  private Company company;

  @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
  @JsonIgnore
  private User owner;

  @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
  @JsonIgnore
  private Set<Sale> sales = new HashSet<>();
}
