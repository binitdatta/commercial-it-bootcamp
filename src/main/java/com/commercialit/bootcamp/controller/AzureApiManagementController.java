package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** Independent instructional endpoint; keeps existing internship mappings unchanged. */
@Controller
public class AzureApiManagementController {
    @GetMapping("/internships/cloud-computing/azure/api-management")
    public String apiManagement() {
        return "internships/azure-api-management";
    }
}
