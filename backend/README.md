# Backend -- WorkFlex Coding Challenge

## Tech Stack

Java 21 · Spring Boot 4 · Spring Data JPA · H2 (dev) · PostgreSQL
(prod-ready) · Swagger · Gradle · Docker

------------------------------------------------------------------------

## Run Locally (Dev)

``` bash
./gradlew bootRun --args='--spring.profiles.active=dev'
```

**Useful URLs** - Swagger: http://localhost:8080/docs - Health:
http://localhost:8080/actuator/health - H2 Console:
http://localhost:8080/h2-console

H2 JDBC: `jdbc:h2:mem:workflex` (user: sa)

------------------------------------------------------------------------

## Run with Docker

``` bash
docker build -t workflex-backend:local .
docker run --rm -p 8080:8080 -e SPRING_PROFILES_ACTIVE=dev workflex-backend:local
```

------------------------------------------------------------------------

## Architecture

Layered structure: - api → Controllers & DTOs - service → Business
logic - repository → Data access - domain → Entities - exception →
Application exceptions

Centralized exception handling and profile-based configuration.
