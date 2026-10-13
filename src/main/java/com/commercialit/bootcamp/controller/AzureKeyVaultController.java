package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AzureKeyVaultController {

    @GetMapping("/internships/cloud-computing/azure/key-vault")
    public String azureKeyVault() {
        return "internships/azure-key-vault";
    }
}
