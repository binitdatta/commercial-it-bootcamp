package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** Dedicated Azure Data Factory lesson route; existing internship routes remain unchanged. */
@Controller
public class AzureDataFactoryController {

    @GetMapping("/internships/cloud-computing/azure/data-factory")
    public String dataFactoryLesson() {
        return "internships/azure-data-factory";
    }
}
