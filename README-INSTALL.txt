AWS E-Commerce Integration & Messaging Architecture package

Contents:
- Dedicated controller for /internships/cloud-computing/aws/ecommerce-integration-messaging-architecture
- Thymeleaf lesson template using the existing base :: layout(...)
- High-quality PNG architecture diagram preserving the high-traffic e-commerce design while emphasizing EventBridge, Step Functions, SQS, and SNS

This package does NOT overwrite:
- src/main/resources/templates/base.html
- AwsTrainingCatalogController.java
- Existing shared layered menus

Install from the commercial-it-bootcamp project root with:
  unzip -o ~/Downloads/aws-ecommerce-integration-messaging-architecture-complete.zip -d .
  mvn clean package -DskipTests
