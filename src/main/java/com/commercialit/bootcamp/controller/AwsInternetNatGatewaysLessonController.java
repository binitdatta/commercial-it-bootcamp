package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** Dedicated lesson route. Does not alter the existing AWS catalog or shared layout. */
@Controller
public class AwsInternetNatGatewaysLessonController {
    @GetMapping("/internships/cloud-computing/aws/internet-nat-gateways")
    public String internetNatGatewaysLesson() {
        return "internships/cloud-computing/aws/internet-nat-gateways";
    }
}
