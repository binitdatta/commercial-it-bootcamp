package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** Dedicated exact route; intentionally does not modify the existing internship catalog. */
@Controller
public class AwsGlobalInfrastructureLessonController {
    @GetMapping("/internships/cloud-computing/aws/global-infrastructure")
    public String globalInfrastructureLesson() {
        return "internships/lesson-aws-global-infrastructure";
    }
}
