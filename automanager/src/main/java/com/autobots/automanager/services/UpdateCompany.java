package com.autobots.automanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entities.Company;

@Service
public class UpdateCompany {
  @Autowired
  private VerifyStringNull verifyStringNull;
  @Autowired
  private UpdateAddress updateAddress;
  @Autowired
  private UpdatePhone updatePhone;

  public void update(Company company, Company updatedCompany) {
    updateFields(company, updatedCompany);
    updateAddress.update(company.getAddress(), updatedCompany.getAddress());
    updatePhone.update(company.getPhones(), updatedCompany.getPhones());
  }

  public void updateFields(Company company, Company update) {
    if (update != null) {
      if (!verifyStringNull.verify(update.getLegalName())) {
        company.setLegalName(update.getLegalName());
      }
      if (!verifyStringNull.verify(update.getTradeName())) {
        company.setTradeName(update.getTradeName());
      }
    }
  }
}
