package com.optimagrowth.license.service;

import com.optimagrowth.license.model.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Random;

@Service
public class LicenseService {


    @Autowired
    MessageSource messageResource;

    public License getLicense(String licenseId, String organizationId) {
        License license = new License();
        license.setId(new Random().nextInt(1000));
        license.setLicenseId(licenseId);
        license.setOrganizationId(organizationId);
        license.setDescription("Software Product");
        license.setProductName("OStock");
        license.setLicenseType("full");

        return license;
    }

    public String createLicense(License license, String organizationId, Locale locale) {
        String responseMessage = null;
        if(license != null){
            license.setOrganizationId(organizationId);
            responseMessage = String.format(messageResource.getMessage("license.create.message", null, locale), license.toString());
        }
        return responseMessage;
    }

    public String updateLicense(License license, String organizationId){
        String responseMessage = null;
        if(license != null){
            license.setOrganizationId(organizationId);
            responseMessage = String.format(messageResource.getMessage("license.update.message", null, null), license.toString());
        }
        return responseMessage;
    }

    public String deleteLicense(String licenseId, String organizationId){
        String responseMessage = null;
        responseMessage = String.format("TDeleting Licence with Id %s for the organization %s", licenseId, organizationId);

        return responseMessage;
    }



}
