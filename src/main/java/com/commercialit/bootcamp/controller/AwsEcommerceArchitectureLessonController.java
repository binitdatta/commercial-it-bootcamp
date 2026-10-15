package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Dedicated route for the AWS high-traffic e-commerce reference architecture lesson.
 * Preserves the shared base layout and existing layered menus.
 */
@Controller
public class AwsEcommerceArchitectureLessonController {

    @GetMapping("/internships/cloud-computing/aws/ecommerce-high-traffic-architecture")
    public String ecommerceArchitectureLesson() {
        return "internships/cloud-computing/aws/ecommerce-high-traffic-architecture";
    }
}
