package com.commercialit.bootcamp.training;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class CourseCatalogService {

    private final List<CourseSection> sections = List.of(
            section("part-1-commercial-it", "Part I — How Commercial IT Works", List.of(
                    lesson(1, "part-1-commercial-it", "it-organization-structure", "IT Organization Structure", "Understand how business, product, architecture, engineering, security, data, operations and support teams fit together."),
                    lesson(2, "part-1-commercial-it", "business-domains", "Business Domains: Retail, Banking, Healthcare & Supply Chain", "Recognize common business processes and learn how engineers acquire domain knowledge."),
                    lesson(3, "part-1-commercial-it", "roles-responsibilities", "Roles and Responsibilities in an IT Shop", "Differentiate Product Owner, Scrum Master, Architect, Developer, QA, DevOps, SRE, Security and related roles."),
                    lesson(4, "part-1-commercial-it", "agile-scrum", "Agile, Scrum, Sprints and Retrospectives", "Understand the ceremonies, artifacts and collaboration model used by many commercial teams."),
                    lesson(5, "part-1-commercial-it", "jira-confluence", "Jira and Confluence in Daily Work", "Follow requirements from backlog through documentation, implementation, review and completion."),
                    lesson(6, "part-1-commercial-it", "sdlc", "Software Development Life Cycle", "Trace a change from business request through analysis, build, test, release and production support."),
                    lesson(7, "part-1-commercial-it", "git-team-development", "Git, Branching, Pull Requests and Team Development", "Use source control safely and understand the workflow around collaborative code changes.")
            )),
            section("part-2-modern-apps", "Part II — How Modern Applications Work", List.of(
                    lesson(8, "part-2-modern-apps", "internet-dns-http-tls", "Internet, DNS, HTTP and TLS", "Follow a browser request from domain lookup through encrypted HTTP communication."),
                    lesson(9, "part-2-modern-apps", "web-app-architecture", "High-Level Web Application Architecture", "Understand browser, edge, UI, API, service, data and integration layers."),
                    lesson(10, "part-2-modern-apps", "rest-api-json", "REST APIs, HTTP Methods, Status Codes and JSON", "Read and reason about practical request/response contracts used by enterprise APIs."),
                    lesson(11, "part-2-modern-apps", "database-sql", "Relational Databases and SQL", "Understand tables, keys, joins, transactions, indexes and core SQL operations."),
                    lesson(12, "part-2-modern-apps", "orm-jpa-hibernate", "ORM, JPA and Hibernate", "Understand how Java objects map to relational data and where ORM abstractions help or hurt."),
                    lesson(13, "part-2-modern-apps", "batch-processing", "Batch Jobs and Scheduled Processing", "Contrast synchronous web requests with scheduled, bulk and reconciliation workloads."),
                    lesson(14, "part-2-modern-apps", "messaging-event-driven", "Messaging and Event-Driven Architecture", "Understand producers, consumers, queues, topics, retries and asynchronous decoupling.")
            )),
            section("part-3-enterprise-development", "Part III — Enterprise Application Development", List.of(
                    lesson(15, "part-3-enterprise-development", "java-refresh", "Java Refresher for Commercial Development", "Review the Java concepts most directly used in Spring-based enterprise applications."),
                    lesson(16, "part-3-enterprise-development", "spring-core-di", "Spring Core and Dependency Injection", "Understand beans, inversion of control, dependency injection and application composition."),
                    lesson(17, "part-3-enterprise-development", "spring-boot-webmvc", "Spring Boot 4 and Spring MVC", "Build controllers, services and web endpoints using modern Spring Boot conventions."),
                    lesson(18, "part-3-enterprise-development", "spring-data-jpa", "Spring Data JPA", "Use repositories, query methods and transactions to implement a clean persistence layer."),
                    lesson(19, "part-3-enterprise-development", "spring-security", "Spring Security Fundamentals", "Understand filter chains, authentication, authorization and secured application boundaries.")
            )),
            section("part-4-enterprise-security", "Part IV — Enterprise Security & IAM", List.of(
                    lesson(20, "part-4-enterprise-security", "security-fundamentals", "Application Security Fundamentals", "Learn least privilege, TLS, secrets, OWASP risks and the shared responsibility model."),
                    lesson(21, "part-4-enterprise-security", "iam", "Identity and Access Management", "Separate identity, authentication, authorization, roles, groups, federation and lifecycle management."),
                    lesson(22, "part-4-enterprise-security", "oauth2", "OAuth 2.0", "Understand clients, authorization servers, resource servers, scopes and authorization grants."),
                    lesson(23, "part-4-enterprise-security", "oidc", "OpenID Connect", "Understand identity tokens, user identity claims and authentication built on OAuth 2.0."),
                    lesson(24, "part-4-enterprise-security", "saml2", "SAML 2.0", "Understand enterprise browser SSO, IdP/SP roles, metadata, assertions, signing and encryption."),
                    lesson(25, "part-4-enterprise-security", "keycloak", "Keycloak Administration and Integration", "Use a real identity provider to model realms, clients, users, roles and federation."),
                    lesson(26, "part-4-enterprise-security", "pkce-jwt", "PKCE, JWT and Token Validation", "Understand proof key protection, signed token claims, issuers, audiences and JWKS validation.")
            )),
            section("part-5-infrastructure", "Part V — Modern Infrastructure", List.of(
                    lesson(27, "part-5-infrastructure", "linux-cli", "Linux and Command-Line Skills", "Become comfortable with the commands and diagnostics common in servers, containers and CI jobs."),
                    lesson(28, "part-5-infrastructure", "docker", "Docker and Containers", "Package an application and understand images, containers, ports, volumes and environment variables."),
                    lesson(29, "part-5-infrastructure", "kubernetes", "Kubernetes Fundamentals", "Recognize pods, deployments, services, ingress, configuration and secrets without becoming a cluster administrator."),
                    lesson(30, "part-5-infrastructure", "cloud-computing", "Cloud Computing Fundamentals", "Understand compute, storage, managed databases, identity and shared responsibility across major clouds."),
                    lesson(31, "part-5-infrastructure", "cloud-networking", "Cloud Networking Fundamentals", "Understand VNets/VPCs, subnets, routing, firewalls, load balancers, private endpoints and DNS.")
            )),
            section("part-6-delivery-operations", "Part VI — Software Delivery & Operations", List.of(
                    lesson(32, "part-6-delivery-operations", "cicd", "CI/CD Pipelines", "Follow code from commit through build, test, scanning, artifact creation and deployment."),
                    lesson(33, "part-6-delivery-operations", "environments", "DEV, TEST, QA, UAT, STAGE and PROD", "Understand why commercial systems use multiple environments and different controls in each."),
                    lesson(34, "part-6-delivery-operations", "testing", "Testing Strategy", "Differentiate unit, integration, API, UI, performance and regression testing."),
                    lesson(35, "part-6-delivery-operations", "logging", "Application Logging", "Write and use logs as evidence for diagnosis rather than as unstructured console noise."),
                    lesson(36, "part-6-delivery-operations", "monitoring-observability", "Monitoring and Observability", "Understand metrics, logs, traces, alerts and service health."),
                    lesson(37, "part-6-delivery-operations", "troubleshooting", "Systematic Troubleshooting", "Use evidence, reproduction, logs, network tools and component isolation to find root causes."),
                    lesson(38, "part-6-delivery-operations", "incident-rca", "Production Incidents and Root Cause Analysis", "Understand incident response, impact, containment, restoration, RCA and corrective action.")
            )),
            section("part-7-data", "Part VII — Data Engineering & Analytics", List.of(
                    lesson(39, "part-7-data", "etl", "ETL and ELT", "Understand extraction, transformation, loading and common enterprise integration patterns."),
                    lesson(40, "part-7-data", "data-warehouse", "Data Warehouses", "Understand dimensional analytics, curated reporting data and warehouse workloads."),
                    lesson(41, "part-7-data", "data-lake", "Data Lakes and Lakehouse Concepts", "Understand object storage, raw/curated zones, medallion architecture and analytical data management."),
                    lesson(42, "part-7-data", "streaming", "Streaming and Near-Real-Time Data", "Understand events, streams, ingestion and how operational data becomes available for fast analytics."),
                    lesson(43, "part-7-data", "analytics-ml", "Analytics and Machine Learning", "Understand how governed business data becomes features, models, predictions and decision support.")
            )),
            section("part-8-ai-engineering", "Part VIII — AI-Era Engineering", List.of(
                    lesson(44, "part-8-ai-engineering", "generative-ai", "Generative AI Fundamentals", "Understand models, tokens, context, prompting, hallucination and responsible use."),
                    lesson(45, "part-8-ai-engineering", "llm-architecture", "LLM Application Architecture", "Understand how applications safely call models through gateways, policies, grounding and observability."),
                    lesson(46, "part-8-ai-engineering", "rag", "Retrieval-Augmented Generation", "Ground model responses using enterprise documents and controlled retrieval."),
                    lesson(47, "part-8-ai-engineering", "tool-calling", "LLM Tool and Function Calling", "Connect models to deterministic APIs, databases and enterprise operations."),
                    lesson(48, "part-8-ai-engineering", "agentic-ai", "Agentic AI", "Understand planning, tool selection, state, validation, retries and human-in-the-loop controls."),
                    lesson(49, "part-8-ai-engineering", "coding-agents", "AI Coding Agents", "Use modern coding assistants while preserving engineering ownership, review and testing."),
                    lesson(50, "part-8-ai-engineering", "vibe-coding", "Vibe Coding: Benefits, Limits and Guardrails", "Move fast with AI-generated code without losing architecture, security, testability or maintainability."),
                    lesson(51, "part-8-ai-engineering", "ai-security-governance", "AI Security and Governance", "Understand data leakage, prompt injection, authorization, auditability and organizational AI controls.")
            )),
            section("part-9-professional-engineering", "Part IX — Professional Engineering", List.of(
                    lesson(52, "part-9-professional-engineering", "architecture-thinking", "Architecture Thinking", "Reason about requirements, constraints, tradeoffs, dependencies and non-functional requirements."),
                    lesson(53, "part-9-professional-engineering", "code-review", "Code Review and Engineering Quality", "Give and receive useful reviews and recognize maintainability, security and correctness risks."),
                    lesson(54, "part-9-professional-engineering", "documentation", "Technical Documentation", "Create documentation that another engineer can operate, troubleshoot and extend."),
                    lesson(55, "part-9-professional-engineering", "working-with-product", "Working with Product and Business Teams", "Translate between business outcomes, requirements, acceptance criteria and technical implementation."),
                    lesson(56, "part-9-professional-engineering", "communication", "Technical Communication", "Communicate clearly with engineers, managers, architects, executives and non-technical stakeholders."),
                    lesson(57, "part-9-professional-engineering", "cybersecurity-mindset", "Cybersecurity Mindset for Every Engineer", "Treat identity, secrets, data, dependencies and operational access as everyday engineering responsibilities.")
            )),
            section("part-10-capstone", "Part X — Commercial Capstone", List.of(
                    lesson(58, "part-10-capstone", "capstone-requirements", "Capstone: Requirements and Jira Backlog", "Convert a business problem into actors, requirements, user stories and acceptance criteria."),
                    lesson(59, "part-10-capstone", "capstone-architecture", "Capstone: Architecture and Security Design", "Design the web, API, database, IAM, deployment and operational architecture before coding."),
                    lesson(60, "part-10-capstone", "capstone-build-operate", "Capstone: Build, Deploy, Operate and Improve", "Implement the system, deploy it, observe it, simulate an incident and complete an RCA.")
            ))
    );

    public List<CourseSection> sections() {
        return sections;
    }

    public int lessonCount() {
        return sections.stream().mapToInt(section -> section.lessons().size()).sum();
    }

    public Optional<CourseLesson> findLesson(String sectionSlug, String lessonSlug) {
        return sections.stream()
            .filter(section -> section.slug().equals(sectionSlug))
            .flatMap(section -> section.lessons().stream())
            .filter(lesson -> lesson.slug().equals(lessonSlug))
            .findFirst();
    }

    public Optional<CourseSection> findSection(String sectionSlug) {
        return sections.stream().filter(section -> section.slug().equals(sectionSlug)).findFirst();
    }

    private static CourseSection section(String slug, String title, List<CourseLesson> lessons) {
        return new CourseSection(slug, title, lessons);
    }

    private static CourseLesson lesson(int number, String sectionSlug, String slug, String title, String objective) {
        return new CourseLesson(number, sectionSlug, slug, title, objective);
    }
}
