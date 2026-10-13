package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** Dedicated IAM and network security lesson; does not alter existing internship routes. */
@Controller
public class AzureIamNetworkSecurityController {

    @GetMapping("/internships/cloud-computing/azure/iam-network-security")
    public String iamNetworkSecurity() {
        return "internships/azure-iam-network-security";
    }
}
