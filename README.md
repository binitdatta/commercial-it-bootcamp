# Commercial IT Bootcamp — Spring Boot 4 Reference Platform

A runnable skeleton for an industry-readiness bootcamp aimed at fresh college graduates and interns. The repository combines a **video-ready training portal** with a **small ecommerce reference implementation** so learners can connect terminology and architecture to working code.

## What is included

- Java 21
- Spring Boot **4.1.1**
- Spring MVC + Thymeleaf
- Bootstrap **5.3.8** using a local WebJar plus a bright-blue custom theme
- Spring Security
- Keycloak OIDC login using Authorization Code + **PKCE**
- Spring OAuth2 Resource Server JWT validation for `/api/**`
- Keycloak realm import JSON with three demo human users and a demo machine client
- Spring Data JPA / Hibernate
- MySQL 8.x schema and seed data
- Models, repositories, services, MVC controllers and REST controllers
- 10 training sections containing **60 individual Thymeleaf video pages**
- Training and E-Commerce menus kept separate in the top navbar
- Optional Docker Compose file for MySQL + Keycloak only
- `.env.example` documenting all local application overrides

> This repository is a **training/reference implementation**, not a production ecommerce platform. Demo passwords and a demo API client secret are intentionally present for local classroom use. Replace them for any non-local environment.

---

## 1. Project structure

```text
commercial-it-bootcamp/
├── database/
│   ├── 01-schema.sql
│   └── 02-seed.sql
├── keycloak/
│   └── commercial-it-bootcamp-realm.json
├── src/main/java/com/commercialit/bootcamp/
│   ├── config/                  # Spring Security configuration
│   ├── controller/              # Thymeleaf MVC controllers
│   ├── dto/                     # REST request/response contracts
│   ├── exception/
│   ├── model/                   # JPA entities and enums
│   ├── repository/              # Spring Data repositories
│   ├── rest/                    # REST controllers
│   ├── service/                 # transaction/business layer
│   └── training/                # course navigation/catalog model
├── src/main/resources/
│   ├── static/css/bootcamp.css
│   ├── static/js/bootcamp.js
│   ├── templates/base.html      # shared Thymeleaf base layout
│   ├── templates/training/      # 60 individual future video pages
│   └── templates/ecommerce/     # reference application pages
├── application.yml
├── docker-compose-infra.yml
└── pom.xml
```

---

## 2. Prerequisites

Install or have available:

1. **JDK 21**
2. **Maven 3.9+** (or use the Maven integration in IntelliJ IDEA / Eclipse / Spring Tools)
3. **MySQL 8.x**
4. **Keycloak 26.x** (the supplied optional Compose file uses 26.7.3)

Default local ports:

| Component | Port |
|---|---:|
| Keycloak | 8080 |
| Spring Boot app | 8081 |
| MySQL | 3306 |

The app runs on **8081** so Keycloak can use its normal **8080** development port.

---

# Option A — Fastest local setup with Docker Compose

This starts only the infrastructure. Run Spring Boot itself from your IDE so students can debug it easily.

## 3A. Start MySQL and Keycloak

From the project root:

```bash
docker compose -f docker-compose-infra.yml up -d
```

Check containers:

```bash
docker compose -f docker-compose-infra.yml ps
```

The MySQL container automatically executes:

```text
database/01-schema.sql
database/02-seed.sql
```

The Keycloak container automatically imports:

```text
keycloak/commercial-it-bootcamp-realm.json
```

Open the Keycloak Admin Console:

```text
http://localhost:8080/admin/
```

Bootstrap administrator:

```text
username: admin
password: admin
```

Confirm the realm exists:

```text
commercial-it-bootcamp
```

> If you previously created the Docker volume and later changed the SQL seed files, MySQL will not rerun initialization scripts on an existing data volume. For a clean classroom reset use `docker compose -f docker-compose-infra.yml down -v`, then start it again. This deletes the local demo database volume.

---

# Option B — Use existing MySQL and Keycloak installations

## 3B. Create the MySQL schema manually

Using the MySQL command-line client:

```bash
mysql -u root -p < database/01-schema.sql
mysql -u root -p < database/02-seed.sql
```

The scripts create and populate:

```text
commercial_it_bootcamp
```

The default Spring configuration expects:

```text
DB_USERNAME=bootcamp
DB_PASSWORD=bootcamp
```

If that account does not exist in your own MySQL installation, either create it or override the application environment variables.

Example account creation as a MySQL administrator:

```sql
CREATE USER IF NOT EXISTS 'bootcamp'@'localhost' IDENTIFIED BY 'bootcamp';
GRANT ALL PRIVILEGES ON commercial_it_bootcamp.* TO 'bootcamp'@'localhost';
FLUSH PRIVILEGES;
```

## 4B. Import the Keycloak realm manually

The supplied file follows the Keycloak realm import convention:

```text
keycloak/commercial-it-bootcamp-realm.json
```

### Import with the Keycloak CLI

Stop Keycloak before an offline import, then run from the Keycloak installation directory:

```bash
bin/kc.sh import --file /absolute/path/to/commercial-it-bootcamp/keycloak/commercial-it-bootcamp-realm.json
```

On Windows:

```bat
bin\kc.bat import --file C:\path\to\commercial-it-bootcamp\keycloak\commercial-it-bootcamp-realm.json
```

Then start Keycloak in development mode:

```bash
bin/kc.sh start-dev
```

You can also import the realm from the Keycloak Admin Console if you prefer the UI.

---

## 5. Demo Keycloak identities

Realm:

```text
commercial-it-bootcamp
```

Human UI client:

```text
commercial-it-bootcamp
```

The UI client is a **public OAuth client** and requires **PKCE S256**. Spring Security automatically uses PKCE because `client-authentication-method` is `none` and the client has no secret.

Demo users:

| Username | Password | Realm roles |
|---|---|---|
| `student` | `Student123!` | student |
| `instructor` | `Instructor123!` | student, instructor |
| `bootcamp-admin` | `Admin123!` | student, instructor, admin |

Machine/API demo client:

```text
client_id: commercial-it-bootcamp-api
client_secret: demo-api-secret-change-me
```

Again: that secret is only for local training.

---

## 6. Configure the application

Defaults are already provided in:

```text
src/main/resources/application.yml
```

You normally do not need to change anything when using the supplied Docker Compose infrastructure.

Supported environment overrides:

```bash
export APP_PORT=8081
export DB_URL='jdbc:mysql://localhost:3306/commercial_it_bootcamp?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC'
export DB_USERNAME=bootcamp
export DB_PASSWORD=bootcamp
export KEYCLOAK_ISSUER_URI='http://localhost:8080/realms/commercial-it-bootcamp'
export KEYCLOAK_JWK_SET_URI='http://localhost:8080/realms/commercial-it-bootcamp/protocol/openid-connect/certs'
```

The JPA setting is deliberately:

```yaml
spring.jpa.hibernate.ddl-auto: validate
```

That means Hibernate **validates** the database mapping but does not silently create or mutate your schema. This is useful for teaching the difference between database DDL and ORM mapping.

---

## 7. Open in an IDE

### IntelliJ IDEA

1. **File → Open**
2. Select the extracted `commercial-it-bootcamp` directory.
3. Allow IntelliJ to import the Maven project.
4. Confirm Project SDK = **Java 21**.
5. Locate:

```text
com.commercialit.bootcamp.CommercialItBootcampApplication
```

6. Run the `main()` method.

### Eclipse / Spring Tools

1. **File → Import → Existing Maven Projects**
2. Select the project directory.
3. Confirm Java 21.
4. Run `CommercialItBootcampApplication` as a Spring Boot application.

### Command line

```bash
mvn clean spring-boot:run
```

---

## 8. Required startup order

For the default configuration, use this order:

```text
1. MySQL
2. Keycloak
3. Spring Boot application
```

The OIDC client configuration uses the Keycloak issuer metadata, so Keycloak should be reachable when the application starts.

---

## 9. Open the application

Home:

```text
http://localhost:8081/
```

Sign in:

```text
http://localhost:8081/login
```

Training catalog:

```text
http://localhost:8081/training
```

E-Commerce reference app:

```text
http://localhost:8081/ecommerce
```

Actuator health:

```text
http://localhost:8081/actuator/health
```

---

## 10. Security architecture

This project demonstrates two different Spring Security roles in one application.

### Browser / Thymeleaf UI

```text
Browser
   |
   | Authorization Code + PKCE
   v
Keycloak
   |
   | ID Token / Access Token
   v
Spring Security OAuth2 Login
   |
   v
Authenticated Thymeleaf Session
```

Training and ecommerce pages require one of these realm roles:

```text
student
instructor
admin
```

`SecurityConfig` maps Keycloak `realm_access.roles` into Spring authorities such as:

```text
ROLE_STUDENT
ROLE_INSTRUCTOR
ROLE_ADMIN
```

### REST API

```text
API Client
   |
   | Authorization: Bearer <JWT>
   v
Spring Security OAuth2 Resource Server
   |
   | validate issuer + signature through Keycloak JWKS
   v
/api/**
```

This lets the same codebase teach both browser SSO and secured API patterns.

---

## 11. Try the REST API

### Obtain a client-credentials access token

```bash
TOKEN=$(curl -s -X POST   http://localhost:8080/realms/commercial-it-bootcamp/protocol/openid-connect/token   -H 'Content-Type: application/x-www-form-urlencoded'   -d 'grant_type=client_credentials'   -d 'client_id=commercial-it-bootcamp-api'   -d 'client_secret=demo-api-secret-change-me'   | python3 -c "import sys,json; print(json.load(sys.stdin)['access_token'])")
```

### List products

```bash
curl -s http://localhost:8081/api/products   -H "Authorization: Bearer $TOKEN"
```

### List customers

```bash
curl -s http://localhost:8081/api/customers   -H "Authorization: Bearer $TOKEN"
```

### List orders

```bash
curl -s http://localhost:8081/api/orders   -H "Authorization: Bearer $TOKEN"
```

### Create an order

```bash
curl -s -X POST http://localhost:8081/api/orders   -H "Authorization: Bearer $TOKEN"   -H 'Content-Type: application/json'   -d '{
    "customerId": 1,
    "lines": [
      {"productId": 5, "quantity": 1},
      {"productId": 6, "quantity": 1}
    ]
  }'
```

The service checks product status and stock, calculates tax/shipping, saves the order and decreases inventory inside a transaction.

---

## 12. Ecommerce domain model

```text
Category 1 ---- * Product
Customer 1 ---- * Address
Customer 1 ---- * CustomerOrder
CustomerOrder 1 ---- * OrderItem
Product 1 ---- * OrderItem
```

Primary database tables:

```text
categories
products
customers
addresses
customer_orders
order_items
```

The code intentionally demonstrates the common layering pattern:

```text
MVC Controller / REST Controller
              |
           Service
              |
          Repository
              |
         JPA / Hibernate
              |
            MySQL
```

---

## 13. Training-page architecture

The top navbar has two separate primary dropdowns:

```text
Training
E-Commerce Reference
```

The Training menu then nests the 10 curriculum parts and all 60 lessons.

Every video page is a separate file, for example:

```text
src/main/resources/templates/training/part-2-modern-apps/internet-dns-http-tls.html
src/main/resources/templates/training/part-4-enterprise-security/oauth2.html
src/main/resources/templates/training/part-8-ai-engineering/agentic-ai.html
```

All pages use the same base:

```text
src/main/resources/templates/base.html
```

This is intentional. When expanding a lesson later, keep the same outer line:

```html
<html xmlns:th="http://www.thymeleaf.org"
      th:replace="~{base :: layout('Lesson title', 'Description', ~{::main})}">
```

and replace only the `<main>...</main>` lesson content.

That allows future lesson-generation work to remain consistent across the entire course.

---

## 14. Suggested way to evolve each lesson

Each placeholder already recommends the same teaching contract:

1. Business or engineering problem
2. Core terminology
3. Architecture or workflow
4. Working demonstration
5. Common failure modes
6. Practice task and recap

This keeps the program oriented toward **rapid commercial productivity**, not technology memorization.

---

## 15. Where to extend the reference application

Useful future capstone additions include:

- Shopping cart and checkout UI
- Product administration restricted to `ROLE_ADMIN`
- Customer-specific authorization
- Order-state transition policy
- Flyway/Liquibase migrations
- Spring Batch job
- RabbitMQ or Kafka order event
- Dockerfile for the Spring application
- Kubernetes deployment/service manifests
- CI/CD workflow
- OpenTelemetry tracing
- Prometheus/Grafana or another observability stack
- GenAI customer-service assistant
- Agentic workflow with explicit tool authorization and audit logging

---

## 16. Troubleshooting

### Application cannot connect to MySQL

Verify:

```bash
mysql -h 127.0.0.1 -P 3306 -u bootcamp -p commercial_it_bootcamp
```

Then confirm `DB_URL`, `DB_USERNAME` and `DB_PASSWORD`.

### Application fails while resolving the Keycloak issuer

Open:

```text
http://localhost:8080/realms/commercial-it-bootcamp/.well-known/openid-configuration
```

If that does not return JSON, verify Keycloak is running and the realm was imported.

### Keycloak says redirect URI is invalid

The supplied client expects:

```text
http://localhost:8081/login/oauth2/code/keycloak
```

If you change the Spring Boot port or hostname, update the Keycloak client's valid redirect URIs too.

### 403 after successful login

Confirm the user has at least one realm role:

```text
student
instructor
admin
```

Then sign out and sign in again so a new token/session contains the current roles.

### Hibernate schema validation fails

Run both database scripts and compare the failing column/table with the JPA entity mapping. `ddl-auto=validate` is intentionally strict.

---

## 17. Clean local reset when using Docker Compose

```bash
docker compose -f docker-compose-infra.yml down -v
docker compose -f docker-compose-infra.yml up -d
```

Then restart the Spring Boot application.

---

## 18. Important training/security notes

- Do not use the demo passwords or API secret outside local training.
- Do not commit real credentials, tokens, certificates or production configuration.
- The public browser client uses PKCE and has no client secret.
- The machine client exists only to make bearer-token API exercises easy to reproduce.
- SAML 2.0 is part of the training navigation but is not wired into this starter application; the working UI authentication reference is OIDC.
- Production ecommerce systems require substantially more validation, authorization, audit, fraud, privacy, resilience and operational controls than this teaching sample.

---

## 19. First lesson-development workflow

When you return to expand an individual video, identify it by path or title, for example:

```text
Part IV / OAuth 2.0
```

or:

```text
src/main/resources/templates/training/part-4-enterprise-security/oauth2.html
```

The lesson can then be developed without redesigning the application shell.
