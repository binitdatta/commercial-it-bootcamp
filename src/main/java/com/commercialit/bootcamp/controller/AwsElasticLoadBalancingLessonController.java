package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AwsElasticLoadBalancingLessonController {
    @GetMapping("/internships/cloud-computing/aws/elastic-load-balancing")
    public String lesson() {
        return "internships/cloud-computing/aws/elastic-load-balancing";
    }
}
