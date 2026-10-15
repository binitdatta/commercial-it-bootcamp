package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** Exact-path AWS EC2 lesson; shared application navigation is defined by base.html. */
@Controller
public class AwsEc2VirtualMachinesLessonController {
    @GetMapping("/internships/cloud-computing/aws/ec2")
    public String ec2Lesson() {
        return "internships/cloud-computing/aws/ec2";
    }
}
