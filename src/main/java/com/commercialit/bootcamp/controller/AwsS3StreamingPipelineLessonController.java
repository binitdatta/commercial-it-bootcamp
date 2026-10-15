package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Dedicated lesson route for Amazon S3 streaming analytics pipeline.
 * Preserves the shared base layout and existing layered menus.
 */
@Controller
public class AwsS3StreamingPipelineLessonController {

    @GetMapping("/internships/cloud-computing/aws/s3")
    public String s3StreamingPipelineLesson() {
        return "internships/cloud-computing/aws/s3";
    }
}
