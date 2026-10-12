package com.commercialit.bootcamp.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.commercialit.bootcamp.training.CourseCatalogService;

@Controller
public class HomeController {
    private final CourseCatalogService courseCatalogService;

    public HomeController(CourseCatalogService courseCatalogService) {
        this.courseCatalogService = courseCatalogService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("lessonCount", courseCatalogService.lessonCount());
        model.addAttribute("sectionCount", courseCatalogService.sections().size());
        return "home";
    }

    @GetMapping("/login")
    public String login(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken)) {
            return "redirect:/training";
        }
        return "login";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }
}
