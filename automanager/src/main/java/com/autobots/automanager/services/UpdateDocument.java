package com.autobots.automanager.services;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entities.Document;

@Service
public class UpdateDocument {
  @Autowired
  private VerifyStringNull verifyStringNull;

  public void update(Document document, Document update) {
    if (update != null) {
      if (!verifyStringNull.verify(update.getType().toString())) {
        document.setType(update.getType());
      }
      if (!verifyStringNull.verify(update.getNumber())) { 
        document.setNumber(update.getNumber());
      }
    }
  }

  public void update(Set<Document> documents, Set<Document> updates) {
    for (Document update : updates) {
      for (Document document : documents) {
        if (update.getId() != null && update.getId().equals(document.getId())) {
          update(document, update);
        }
      }
    }
  }
}
