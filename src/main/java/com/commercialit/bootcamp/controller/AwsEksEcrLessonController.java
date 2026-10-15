package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Dedicated lesson routes for Amazon EKS and Amazon ECR.
 * This controller does not alter the shared layout or existing training catalog controller.
 */
@Controller
public class AwsEksEcrLessonController {

    @GetMapping("/internships/cloud-computing/aws/eks")
    public String eksLesson() {
        return "internships/cloud-computing/aws/eks";
    }

    @GetMapping("/internships/cloud-computing/aws/ecr")
    public String ecrLesson() {
        return "internships/cloud-computing/aws/ecr";
    }
}
