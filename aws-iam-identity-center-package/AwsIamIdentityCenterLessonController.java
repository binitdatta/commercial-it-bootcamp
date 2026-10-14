package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AwsIamIdentityCenterLessonController {
    @GetMapping("/internships/cloud-computing/aws/iam-identity-center")
    public String iamIdentityCenterLesson() {
        return "internships/lesson-aws-iam-identity-center";
    }
}
