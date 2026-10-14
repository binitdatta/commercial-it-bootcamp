package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** Exact lesson route; preserves the existing AWS catalog controller and shared layout. */
@Controller
public class AwsSecurityGroupsNaclsLessonController {
    @GetMapping("/internships/cloud-computing/aws/security-groups-nacls")
    public String securityGroupsAndNacls() {
        return "internships/cloud-computing/aws/security-groups-nacls";
    }
}
