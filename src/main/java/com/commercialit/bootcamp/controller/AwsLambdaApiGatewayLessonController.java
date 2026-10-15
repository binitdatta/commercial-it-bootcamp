package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** Exact URL mappings; existing AwsTrainingCatalogController and shared base.html remain unchanged. */
@Controller
public class AwsLambdaApiGatewayLessonController {
    @GetMapping("/internships/cloud-computing/aws/lambda")
    public String lambdaLesson() {
        return "internships/cloud-computing/aws/lambda";
    }

    @GetMapping("/internships/cloud-computing/aws/api-gateway")
    public String apiGatewayLesson() {
        return "internships/cloud-computing/aws/api-gateway";
    }
}
