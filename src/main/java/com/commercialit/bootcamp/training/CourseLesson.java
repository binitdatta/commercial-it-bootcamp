package com.commercialit.bootcamp.training;

public record CourseLesson(
        int number,
        String sectionSlug,
        String slug,
        String title,
        String objective) {

    public String path() {
        return "/training/" + sectionSlug + "/" + slug;
    }

    public String getPath() {
        return path();
    }
}
