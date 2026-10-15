package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** Dedicated lesson route. Does not alter the existing AWS catalog or shared layout. */
@Controller
public class AwsEc2AutoScalingLessonController {
    @GetMapping("/internships/cloud-computing/aws/auto-scaling")
    public String autoScalingLesson() {
        return "internships/cloud-computing/aws/auto-scaling";
    }
}
