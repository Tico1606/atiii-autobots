package com.autobots.automanager.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CredentialUserPassword extends Credential {

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String password;
}
