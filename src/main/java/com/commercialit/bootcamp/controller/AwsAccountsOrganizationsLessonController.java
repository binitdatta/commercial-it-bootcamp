package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/internships/cloud-computing/aws")
public class AwsAccountsOrganizationsLessonController {

    @GetMapping("/accounts-organizations")
    public String accountsOrganizationsLesson() {
        return "internships/lesson-aws-accounts-organizations";
    }
}
