package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** Azure networking lesson: separate route, no changes to existing controllers. */
@Controller
public class AzureVirtualNetworkController {

    @GetMapping("/internships/cloud-computing/azure/virtual-network-isolation")
    public String virtualNetworkIsolation() {
        return "internships/azure-virtual-network-isolation";
    }
}
