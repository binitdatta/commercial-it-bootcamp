package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AzureInfrastructureNetworkingController {
    @GetMapping("/internships/cloud-computing/azure/infrastructure-networking")
    public String infrastructureNetworking() {
        return "internships/azure-infrastructure-networking";
    }
}
