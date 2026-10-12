package com.commercialit.bootcamp.training;

import java.util.List;

public record CourseSection(String slug, String title, List<CourseLesson> lessons) {
}
