package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Dedicated route for a high-traffic AWS e-commerce architecture lesson
 * emphasizing integration, messaging, and real-time analytics.
 * This controller is additive and does not modify existing menus or shared layout.
 */
@Controller
public class AwsEcommerceIntegrationMessagingLessonController {

    @GetMapping("/internships/cloud-computing/aws/ecommerce-integration-messaging-architecture")
    public String ecommerceIntegrationMessagingArchitecture() {
        return "internships/cloud-computing/aws/ecommerce-integration-messaging-architecture";
    }
}
