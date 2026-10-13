package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AzureEventHubsController {

    @GetMapping("/internships/cloud-computing/azure/event-hubs")
    public String eventHubs() {
        return "internships/azure-event-hubs";
    }
}
