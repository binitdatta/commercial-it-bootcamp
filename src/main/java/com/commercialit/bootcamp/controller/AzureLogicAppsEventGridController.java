package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** Standalone training page; does not modify existing internship routes. */
@Controller
public class AzureLogicAppsEventGridController {
    @GetMapping("/internships/cloud-computing/azure/logic-apps-event-grid")
    public String lesson() {
        return "internships/azure-logic-apps-event-grid";
    }
}
