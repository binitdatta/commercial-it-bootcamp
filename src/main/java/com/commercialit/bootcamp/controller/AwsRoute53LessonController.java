package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AwsRoute53LessonController {
    @GetMapping("/internships/cloud-computing/aws/route-53")
    public String lesson() {
        return "internships/cloud-computing/aws/route-53";
    }
}
