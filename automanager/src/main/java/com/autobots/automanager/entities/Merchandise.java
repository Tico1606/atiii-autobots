package com.autobots.automanager.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class Merchandise extends RepresentationModel<Merchandise> {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Date expirationDate;

  @Column(nullable = false)
  private Date manufactureDate;

  @Column(nullable = false)
  private Date registrationDate;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private long quantity;

  @Column(nullable = false)
  private double price;

  @Column
  private String description;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "company_id", nullable = false)
  private Company company;
}
