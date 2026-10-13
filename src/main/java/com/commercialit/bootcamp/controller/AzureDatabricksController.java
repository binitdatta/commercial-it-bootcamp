package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AzureDatabricksController {
    @GetMapping("/internships/cloud-computing/azure/databricks")
    public String databricks() {
        return "internships/azure-databricks";
    }
}
