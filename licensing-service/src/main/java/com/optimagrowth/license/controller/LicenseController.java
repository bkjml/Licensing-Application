package com.optimagrowth.license.controller;

import com.optimagrowth.license.model.License;
import com.optimagrowth.license.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/v1/organization/{organizationId}/license")
public class LicenseController {

    @Autowired
    private LicenseService licenseService;

    @GetMapping("/{licenseId}")
    public ResponseEntity<License> getLicense(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId){

        License license = licenseService.getLicense(licenseId, organizationId);

        license.add(linkTo(methodOn(LicenseController.class)
                .getLicense(organizationId, license.getLicenseId()))
                .withSelfRel(),
                linkTo(methodOn(LicenseController.class)
                        .createLicense(organizationId, license, null))
                        .withRel("Create License"),
                linkTo(methodOn(LicenseController.class)
                        .updateLicence(organizationId, license))
                        .withRel("Update License"),
                linkTo(methodOn(LicenseController.class)
                        .deleteLicense(license.getLicenseId(), organizationId))
                        .withRel("Delete License"));

        return ResponseEntity.ok(license);
    }

    @PutMapping
    public ResponseEntity<String> updateLicence(@PathVariable String organizationId, @RequestBody License request){
        return ResponseEntity.ok(licenseService.updateLicense(request, organizationId));
    }

    @PostMapping
    public ResponseEntity<String> createLicense(@PathVariable String organizationId, @RequestBody License request, @RequestHeader(value = "Accept-Language", required = false) Locale locale){
        return ResponseEntity.ok(licenseService.createLicense(request, organizationId, locale));
    }

    @DeleteMapping("/{licenceId}")
    public ResponseEntity<String> deleteLicense(@PathVariable String licenceId, @PathVariable String organizationId){
        return ResponseEntity.ok(licenseService.deleteLicense(licenceId, organizationId));
    }


}
