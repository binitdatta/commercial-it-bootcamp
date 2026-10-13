package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** Dedicated AKS lesson endpoint; existing internship routes are not modified. */
@Controller
public class AzureAksController {
    @GetMapping("/internships/cloud-computing/azure/aks-kubernetes")
    public String azureAksKubernetes() {
        return "internships/azure-aks-kubernetes";
    }
}
