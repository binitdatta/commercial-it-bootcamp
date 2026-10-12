package com.commercialit.bootcamp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.commercialit.bootcamp.training.CourseCatalogService;
import com.commercialit.bootcamp.training.CourseLesson;

@Controller
@RequestMapping("/training")
public class TrainingController {
    private final CourseCatalogService catalog;

    public TrainingController(CourseCatalogService catalog) {
        this.catalog = catalog;
    }

    @GetMapping
    public String overview(Model model) {
        model.addAttribute("sections", catalog.sections());
        model.addAttribute("lessonCount", catalog.lessonCount());
        return "training/index";
    }

    @GetMapping("/{sectionSlug}/{lessonSlug}")
    public String lesson(@PathVariable String sectionSlug,
                         @PathVariable String lessonSlug,
                         Model model) {
        CourseLesson lesson = catalog.findLesson(sectionSlug, lessonSlug)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Training lesson not found"));
        model.addAttribute("lesson", lesson);
        model.addAttribute("section", catalog.findSection(sectionSlug).orElseThrow());
        return "training/" + sectionSlug + "/" + lessonSlug;
    }
}
