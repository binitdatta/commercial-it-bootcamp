package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** Dedicated exact-path route; existing AWS catalog and base layout remain unchanged. */
@Controller
public class AwsEbsVolumesLessonController {
    @GetMapping("/internships/cloud-computing/aws/ebs")
    public String ebsLesson() {
        return "internships/cloud-computing/aws/ebs";
    }
}
