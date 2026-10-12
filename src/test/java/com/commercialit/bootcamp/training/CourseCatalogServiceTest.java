package com.commercialit.bootcamp.training;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CourseCatalogServiceTest {

    @Test
    void catalogContainsTenSectionsAndSixtyLessons() {
        CourseCatalogService catalog = new CourseCatalogService();
        assertEquals(10, catalog.sections().size());
        assertEquals(60, catalog.lessonCount());
        assertTrue(catalog.findLesson("part-4-enterprise-security", "oauth2").isPresent());
    }
}
