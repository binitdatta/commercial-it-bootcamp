package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** Dedicated multi-region Azure App Service architecture lesson. */
@Controller
public class AzureAppServiceHaController {
    @GetMapping("/internships/cloud-computing/azure/app-service-high-availability")
    public String appServiceHighAvailability() {
        return "internships/azure-app-service-high-availability";
    }
}
