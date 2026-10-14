# AWS Global Infrastructure lesson (Spring Boot / Thymeleaf)

Install from the commercial-it-bootcamp project root:

- `aws-global-infrastructure.html` -> `src/main/resources/templates/internships/`
- `lesson-aws-global-infrastructure.html` -> `src/main/resources/templates/internships/`
- `AwsGlobalInfrastructureLessonController.java` -> `src/main/java/com/commercialit/bootcamp/controller/`
- `aws-global-infrastructure.svg` -> `src/main/resources/static/images/`

Exact route: `/internships/cloud-computing/aws/global-infrastructure`

This intentionally does NOT modify `base.html`, `topic.html`, `section.html`,
`InternshipController.java`, the AWS overview, or any other AWS/GCP pages.
It uses one exact Spring MVC route, which is more specific than the existing
catalog's variable-slug fallback route. If a different controller already
registers the SAME exact URL, resolve that collision before compiling/running.

Build normally with `mvn clean package` and restart. Click the image for full-size zoom.
