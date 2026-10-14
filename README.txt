AWS Elastic Load Balancing + Route 53 lesson bundle

Run from Spring Boot project root:
  unzip -o ~/Downloads/aws-elb-route53-lessons.zip -d .
  mvn clean package -DskipTests

Restart application, then open:
  http://localhost:8081/internships/cloud-computing/aws/elastic-load-balancing
  http://localhost:8081/internships/cloud-computing/aws/route-53

Does not include or replace base.html, AwsTrainingCatalogController.java, or prior lesson templates.
Both HTML pages use the verified shared Thymeleaf layout: base :: layout(...).
The exact-path controllers take precedence over the generic /aws/{lessonSlug} handler.

Diagram assets: separate high-resolution PNG (3200 x 1960) and SVG for each lesson.
