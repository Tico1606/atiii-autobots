package com.autobots.automanager.services;

import java.util.Set;

public interface LinkAdder<T> {
  public void addLink(Set<T> lista);

  public void addLink(T objeto);
}
