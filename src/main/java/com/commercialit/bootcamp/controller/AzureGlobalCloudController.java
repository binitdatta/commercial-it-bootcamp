package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** Individual Azure lesson; retains all existing InternshipController mappings. */
@Controller
public class AzureGlobalCloudController {

    @GetMapping("/internships/cloud-computing/azure/global-cloud-architecture")
    public String globalCloudArchitecture() {
        return "internships/azure-global-cloud-architecture";
    }
}
