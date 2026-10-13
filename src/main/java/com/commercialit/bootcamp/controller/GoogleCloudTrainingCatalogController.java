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
public class GoogleCloudTrainingCatalogController {
    private static final String PLATFORM = "Google Cloud";
    private static final Map<String, Topic> TOPICS;

    static {
        Map<String, Topic> topics = new LinkedHashMap<>();
        topics.put("global-infrastructure", new Topic("Regions, Zones & Global Infrastructure", "01 · Cloud Foundations & IAM"));
        topics.put("resource-hierarchy", new Topic("Organizations, Folders & Projects", "01 · Cloud Foundations & IAM"));
        topics.put("iam-service-accounts", new Topic("Cloud IAM & Service Accounts", "01 · Cloud Foundations & IAM"));
        topics.put("org-policy", new Topic("Organization Policy & Resource Governance", "01 · Cloud Foundations & IAM"));
        topics.put("vpc-foundations", new Topic("VPC Networks & IP Ranges", "02 · VPC, Networking & Connectivity"));
        topics.put("subnets-firewall", new Topic("Subnets & VPC Firewall Rules", "02 · VPC, Networking & Connectivity"));
        topics.put("routes-cloud-nat", new Topic("Routes, Cloud Router & Cloud NAT", "02 · VPC, Networking & Connectivity"));
        topics.put("cloud-load-balancing", new Topic("Cloud Load Balancing (L4/L7)", "02 · VPC, Networking & Connectivity"));
        topics.put("cloud-dns", new Topic("Cloud DNS", "02 · VPC, Networking & Connectivity"));
        topics.put("vpc-peering-ncc", new Topic("VPC Peering & Network Connectivity Center", "02 · VPC, Networking & Connectivity"));
        topics.put("cloud-vpn-interconnect", new Topic("Cloud VPN & Cloud Interconnect", "02 · VPC, Networking & Connectivity"));
        topics.put("cloud-armor-cdn", new Topic("Cloud Armor & Cloud CDN", "02 · VPC, Networking & Connectivity"));
        topics.put("private-service-connect", new Topic("Private Service Connect", "02 · VPC, Networking & Connectivity"));
        topics.put("compute-engine", new Topic("Compute Engine VMs", "03 · Virtual Machines & Compute"));
        topics.put("persistent-disk", new Topic("Persistent Disk & Hyperdisk", "03 · Virtual Machines & Compute"));
        topics.put("managed-instance-groups", new Topic("Managed Instance Groups & Autoscaling", "03 · Virtual Machines & Compute"));
        topics.put("app-engine", new Topic("App Engine", "03 · Virtual Machines & Compute"));
        topics.put("gke", new Topic("Google Kubernetes Engine (GKE)", "04 · Containers & Serverless"));
        topics.put("cloud-run", new Topic("Cloud Run", "04 · Containers & Serverless"));
        topics.put("artifact-registry", new Topic("Artifact Registry", "04 · Containers & Serverless"));
        topics.put("cloud-functions", new Topic("Cloud Run Functions", "04 · Containers & Serverless"));
        topics.put("api-gateway-apigee", new Topic("API Gateway & Apigee", "04 · Containers & Serverless"));
        topics.put("cloud-storage", new Topic("Cloud Storage", "05 · Storage, Databases & Caching"));
        topics.put("cloud-sql", new Topic("Cloud SQL", "05 · Storage, Databases & Caching"));
        topics.put("alloydb", new Topic("AlloyDB for PostgreSQL", "05 · Storage, Databases & Caching"));
        topics.put("firestore", new Topic("Firestore", "05 · Storage, Databases & Caching"));
        topics.put("spanner", new Topic("Spanner", "05 · Storage, Databases & Caching"));
        topics.put("memorystore", new Topic("Memorystore", "05 · Storage, Databases & Caching"));
        topics.put("pub-sub", new Topic("Pub/Sub", "06 · Integration & Messaging"));
        topics.put("eventarc", new Topic("Eventarc", "06 · Integration & Messaging"));
        topics.put("workflows", new Topic("Workflows", "06 · Integration & Messaging"));
        topics.put("cloud-tasks-scheduler", new Topic("Cloud Tasks & Cloud Scheduler", "06 · Integration & Messaging"));
        topics.put("dataflow", new Topic("Dataflow", "07 · Data Engineering & AI"));
        topics.put("dataproc", new Topic("Dataproc", "07 · Data Engineering & AI"));
        topics.put("bigquery", new Topic("BigQuery", "07 · Data Engineering & AI"));
        topics.put("data-fusion", new Topic("Cloud Data Fusion", "07 · Data Engineering & AI"));
        topics.put("vertex-ai", new Topic("Vertex AI", "07 · Data Engineering & AI"));
        topics.put("vertex-ai-search", new Topic("Vertex AI Search", "07 · Data Engineering & AI"));
        topics.put("secret-manager-kms", new Topic("Secret Manager & Cloud KMS", "08 · Security, DevOps & Operations"));
        topics.put("cloud-monitoring-logging", new Topic("Cloud Monitoring & Logging", "08 · Security, DevOps & Operations"));
        topics.put("security-command-center", new Topic("Security Command Center", "08 · Security, DevOps & Operations"));
        topics.put("cloud-build-deploy", new Topic("Cloud Build & Cloud Deploy", "08 · Security, DevOps & Operations"));
        topics.put("terraform-infrastructure", new Topic("Infrastructure as Code & Terraform", "08 · Security, DevOps & Operations"));
        topics.put("backup-disaster-recovery", new Topic("Backup and DR Service", "08 · Security, DevOps & Operations"));
        TOPICS = Collections.unmodifiableMap(topics);
    }

    @GetMapping("/internships/cloud-computing/google-cloud/{lessonSlug}")
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
