package com.autobots.automanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.autobots.automanager.entities.User;

@Component
public class UpdateUser {
  @Autowired
  private VerifyStringNull verifyString;

  @Autowired
  private UpdateAddress updateAddress;

  @Autowired
  private UpdateDocument updateDocument;

  @Autowired
  private UpdatePhone updatePhone;

  public void update(User user, User updatedUser) {
    updateData(user, updatedUser);
    updateAddress.update(user.getAddress(), updatedUser.getAddress());
    updateDocument.update(user.getDocuments(), updatedUser.getDocuments());
    updatePhone.update(user.getPhones(), updatedUser.getPhones());
  }

  private void updateData(User user, User updatedUser) {
    if (!verifyString.verify(updatedUser.getName())) {
      user.setName(updatedUser.getName());
    }
    if (!verifyString.verify(updatedUser.getSocialName())) {
      user.setSocialName(updatedUser.getSocialName());
    }
  }
}
