package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** Dedicated static route; does not modify existing internship controllers. */
@Controller
public class GitDeepDiveController {
    @GetMapping("/internships/git-deep-dive")
    public String gitDeepDive() {
        return "internships/lesson-git-deep-dive";
    }
}
