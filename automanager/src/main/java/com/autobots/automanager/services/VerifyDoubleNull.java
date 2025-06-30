package com.autobots.automanager.services;

import org.springframework.stereotype.Service;

@Service
public class VerifyDoubleNull {
  public boolean verify(Double dado) {
    boolean nulo = true;
    if (!(dado == null)) {
      try {
        Double.parseDouble(Double.toString(dado));
        return true;
      } catch (NumberFormatException e) {
        return false;
      }
    }
    return nulo;
  }
}
