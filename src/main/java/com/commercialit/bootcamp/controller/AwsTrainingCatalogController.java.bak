package com.commercialit.bootcamp.controller;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

/** Menu catalog routes. Replace a selected route's view when its full lesson is built. */
@Controller
public class AwsTrainingCatalogController {
    private static final String PLATFORM = "Amazon Web Services";
    private static final Map<String, Topic> TOPICS;

    static {
        Map<String, Topic> topics = new LinkedHashMap<>();
        topics.put("global-infrastructure", new Topic("Regions, AZs & Global Infrastructure", "01 · Cloud Foundations & IAM"));
        topics.put("accounts-organizations", new Topic("AWS Accounts & Organizations", "01 · Cloud Foundations & IAM"));
        topics.put("iam-identity-center", new Topic("IAM & IAM Identity Center", "01 · Cloud Foundations & IAM"));
        topics.put("iam-policies-roles", new Topic("IAM Policies, Roles & STS", "01 · Cloud Foundations & IAM"));
        topics.put("vpc-foundations", new Topic("Amazon VPC & CIDR", "02 · VPC, Networking & Connectivity"));
        topics.put("subnets-route-tables", new Topic("Subnets & Route Tables", "02 · VPC, Networking & Connectivity"));
        topics.put("security-groups-nacls", new Topic("Security Groups & Network ACLs", "02 · VPC, Networking & Connectivity"));
        topics.put("internet-nat-gateways", new Topic("Internet & NAT Gateways", "02 · VPC, Networking & Connectivity"));
        topics.put("elastic-load-balancing", new Topic("Elastic Load Balancing (ALB/NLB)", "02 · VPC, Networking & Connectivity"));
        topics.put("route-53", new Topic("Amazon Route 53 (DNS)", "02 · VPC, Networking & Connectivity"));
        topics.put("transit-gateway-peering", new Topic("Transit Gateway & VPC Peering", "02 · VPC, Networking & Connectivity"));
        topics.put("site-to-site-vpn-direct-connect", new Topic("Site-to-Site VPN & Direct Connect", "02 · VPC, Networking & Connectivity"));
        topics.put("cloudfront-waf", new Topic("CloudFront & AWS WAF", "02 · VPC, Networking & Connectivity"));
        topics.put("ec2", new Topic("Amazon EC2 Virtual Machines", "03 · Virtual Machines & Compute"));
        topics.put("ebs", new Topic("Amazon EBS Volumes", "03 · Virtual Machines & Compute"));
        topics.put("auto-scaling", new Topic("EC2 Auto Scaling", "03 · Virtual Machines & Compute"));
        topics.put("elastic-beanstalk", new Topic("AWS Elastic Beanstalk", "03 · Virtual Machines & Compute"));
        topics.put("ecs-fargate", new Topic("Amazon ECS & Fargate", "04 · Containers & Serverless"));
        topics.put("eks", new Topic("Amazon EKS (Kubernetes)", "04 · Containers & Serverless"));
        topics.put("ecr", new Topic("Amazon ECR", "04 · Containers & Serverless"));
        topics.put("lambda", new Topic("AWS Lambda", "04 · Containers & Serverless"));
        topics.put("api-gateway", new Topic("Amazon API Gateway", "04 · Containers & Serverless"));
        topics.put("s3", new Topic("Amazon S3", "05 · Storage, Databases & Caching"));
        topics.put("rds-aurora", new Topic("Amazon RDS & Aurora", "05 · Storage, Databases & Caching"));
        topics.put("dynamodb", new Topic("Amazon DynamoDB", "05 · Storage, Databases & Caching"));
        topics.put("elasticache", new Topic("Amazon ElastiCache", "05 · Storage, Databases & Caching"));
        topics.put("efs", new Topic("Amazon EFS", "05 · Storage, Databases & Caching"));
        topics.put("sqs", new Topic("Amazon SQS", "06 · Integration & Messaging"));
        topics.put("sns", new Topic("Amazon SNS", "06 · Integration & Messaging"));
        topics.put("eventbridge", new Topic("Amazon EventBridge", "06 · Integration & Messaging"));
        topics.put("step-functions", new Topic("AWS Step Functions", "06 · Integration & Messaging"));
        topics.put("kinesis", new Topic("Amazon Kinesis", "06 · Integration & Messaging"));
        topics.put("glue", new Topic("AWS Glue", "07 · Data Engineering & AI"));
        topics.put("athena-redshift", new Topic("Athena & Redshift", "07 · Data Engineering & AI"));
        topics.put("emr", new Topic("Amazon EMR", "07 · Data Engineering & AI"));
        topics.put("bedrock", new Topic("Amazon Bedrock", "07 · Data Engineering & AI"));
        topics.put("opensearch", new Topic("Amazon OpenSearch Service", "07 · Data Engineering & AI"));
        topics.put("kms-secrets-manager", new Topic("AWS KMS & Secrets Manager", "08 · Security, DevOps & Operations"));
        topics.put("cloudwatch-cloudtrail", new Topic("CloudWatch & CloudTrail", "08 · Security, DevOps & Operations"));
        topics.put("config-security-hub", new Topic("AWS Config & Security Hub", "08 · Security, DevOps & Operations"));
        topics.put("cloudformation-cdk", new Topic("CloudFormation & AWS CDK", "08 · Security, DevOps & Operations"));
        topics.put("codepipeline", new Topic("AWS CodePipeline", "08 · Security, DevOps & Operations"));
        topics.put("backup-disaster-recovery", new Topic("AWS Backup & Disaster Recovery", "08 · Security, DevOps & Operations"));
        TOPICS = Collections.unmodifiableMap(topics);
    }

    @GetMapping("/internships/cloud-computing/aws/{lessonSlug}")
    public String lesson(@PathVariable String lessonSlug, Model model) {
        Topic lesson = TOPICS.get(lessonSlug);
        if (lesson == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unknown cloud lesson");
        }
        model.addAttribute("cloudProvider", PLATFORM);
        model.addAttribute("cloudCategory", lesson.category());
        model.addAttribute("cloudLessonTitle", lesson.title());
        return "internships/cloud-lesson-coming-soon";
    }

    private record Topic(String title, String category) { }
}
