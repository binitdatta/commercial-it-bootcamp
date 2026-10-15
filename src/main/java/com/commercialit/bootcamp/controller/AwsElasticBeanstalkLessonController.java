package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** Dedicated exact-route lesson. Existing AWS catalog controller and base layout are unchanged. */
@Controller
public class AwsElasticBeanstalkLessonController {
    @GetMapping("/internships/cloud-computing/aws/elastic-beanstalk")
    public String elasticBeanstalkLesson() {
        return "internships/cloud-computing/aws/elastic-beanstalk";
    }
}
