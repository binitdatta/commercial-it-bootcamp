package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/internships/cloud-computing/aws")
public class AwsIamPoliciesRolesLessonController {

    @GetMapping("/iam-policies-roles")
    public String awsIamPoliciesRoles() {
        return "internships/aws-iam-policies-roles";
    }
}
