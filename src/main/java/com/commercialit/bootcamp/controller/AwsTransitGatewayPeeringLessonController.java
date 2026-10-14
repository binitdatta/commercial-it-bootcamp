package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** Dedicated exact-path lesson route; leaves the shared AWS catalog and navigation untouched. */
@Controller
public class AwsTransitGatewayPeeringLessonController {

    @GetMapping("/internships/cloud-computing/aws/transit-gateway-peering")
    public String transitGatewayPeeringLesson() {
        return "internships/cloud-computing/aws/transit-gateway-peering";
    }
}
