AWS TRANSIT GATEWAY & VPC PEERING — SPRING BOOT LESSON

Contents:
  src/main/java/com/commercialit/bootcamp/controller/AwsTransitGatewayPeeringLessonController.java
  src/main/resources/templates/internships/cloud-computing/aws/transit-gateway-peering.html
  src/main/resources/static/images/architecture/aws-transit-gateway-peering-architecture.png

The controller defines an EXACT path and does not replace AwsTrainingCatalogController.
The Thymeleaf lesson delegates navbar/footer to existing base :: layout.
No base.html or shared menu files are modified.

From the Spring Boot project root run:
  unzip -o ~/Downloads/aws-transit-gateway-peering-complete.zip -d .
  mvn clean package -DskipTests

Restart the app, then navigate to:
  http://localhost:8081/internships/cloud-computing/aws/transit-gateway-peering

Diagram is AI generated at 1536x1024 then high-quality resampled to 3072x2048 for packaging.
Inspect technical diagrams against live AWS documentation before production implementation.
