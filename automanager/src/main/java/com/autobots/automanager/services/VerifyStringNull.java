package com.autobots.automanager.services;

import org.springframework.stereotype.Service;

@Service
public class VerifyStringNull {
  public boolean verify(String dado) {
    boolean nulo = true;
    if (!(dado == null)) {
      if (!dado.isBlank()) {
        nulo = false;
      }
    }
    return nulo;
  }
}
