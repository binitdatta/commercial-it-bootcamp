package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** Standalone Azure Front Door teaching route; existing internship mappings remain unchanged. */
@Controller
public class AzureFrontDoorController {
    @GetMapping("/internships/cloud-computing/azure/front-door-ddos")
    public String frontDoorLesson() {
        return "internships/azure-front-door-ddos";
    }
}
