package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** Dedicated lesson route. Does not alter the existing AWS catalog or shared layout. */
@Controller
public class AwsSiteToSiteVpnDirectConnectLessonController {
    @GetMapping("/internships/cloud-computing/aws/site-to-site-vpn-direct-connect")
    public String siteToSiteVpnDirectConnectLesson() {
        return "internships/cloud-computing/aws/site-to-site-vpn-direct-connect";
    }
}
