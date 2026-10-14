package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Dedicated route for the completed AWS subnets and route tables lesson.
 * An exact URL mapping takes precedence over the catalog's variable-slug route.
 * Does not alter the shared Thymeleaf layout or other AWS lesson handlers.
 */
@Controller
public class AwsSubnetsRouteTablesLessonController {

    @GetMapping("/internships/cloud-computing/aws/subnets-route-tables")
    public String subnetsRouteTables() {
        return "internships/cloud-computing/aws/subnets-route-tables";
    }
}
