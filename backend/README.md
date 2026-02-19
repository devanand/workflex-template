# Backend (Spring Boot)

This backend is a Spring Boot service that exposes the Workations API
and seeds the database from `workations.csv` on startup (only when the
DB is empty).

------------------------------------------------------------------------

## Prerequisites

-   Java (same version as the project/toolchain)
-   Gradle (wrapper included: `./gradlew`)
-   (Optional) Docker Desktop (for running SonarQube locally)

------------------------------------------------------------------------

## Run in Dev Mode

From the `backend/` folder:

``` bash
./gradlew bootRun --args='--spring.profiles.active=dev'
```

The service starts on the configured port (default: `8080`).

### Verify the API

``` bash
curl -s http://localhost:8080/workflex/workation
```

You should receive a JSON array of workations.

------------------------------------------------------------------------

## Run Tests

### Unit + Integration Tests (default test task)

``` bash
./gradlew clean test
```

If you have a dedicated `integrationTest` task configured:

``` bash
./gradlew clean test integrationTest
```

------------------------------------------------------------------------

## Test Coverage (JaCoCo)

Generate coverage reports:

``` bash
./gradlew clean test jacocoTestReport
```

### View HTML coverage report

Open:

build/reports/jacoco/test/html/index.html

### JaCoCo XML report path

Sonar uses the XML report at:

build/reports/jacoco/test/jacocoTestReport.xml

------------------------------------------------------------------------

## SonarQube (Local)

### 1) Start SonarQube using Docker

Make sure Docker Desktop is running, then:

``` bash
docker run -d --name sonarqube -p 9000:9000 sonarqube:lts-community
```

If the container already exists:

``` bash
docker start sonarqube
```

### 2) Verify SonarQube is up

``` bash
curl -s http://localhost:9000/api/system/status
```

Wait until it returns:

{"status":"UP"}

Open the UI:

http://localhost:9000

### 3) Run analysis

Export your token (create a token in SonarQube UI → My Account →
Security):

``` bash
export SONAR_TOKEN="YOUR_TOKEN"
```

Run Sonar (recommended: run tests + coverage first):

``` bash
./gradlew clean test jacocoTestReport sonar
```

If you also have integration tests and want them included:

``` bash
./gradlew clean test integrationTest jacocoAllReport sonar
```

### 4) Coverage in SonarQube

After analysis completes, open the project in SonarQube UI and check: -
Coverage % - Unit test execution - Bugs / Vulnerabilities / Code Smells

------------------------------------------------------------------------

## Common Issues

### Port 8080 already in use

Kill the process on 8080 or run on another port:

``` bash
./gradlew bootRun --args='--server.port=8081 --spring.profiles.active=dev'
```

### SonarQube not reachable

If Gradle shows an error connecting to http://localhost:9000, ensure: -
Docker Desktop is running - SonarQube container is running: docker ps \|
grep sonarqube

------------------------------------------------------------------------

## Project Structure (High Level)

-   api/ -- REST controllers + API DTOs\
-   service/ -- Business logic\
-   persistence/ -- Repositories\
-   domain/ -- Entities + Enums\
-   importer/ -- CSV parsing + startup seeding

# Backend (Spring Boot)

![Coverage](https://img.shields.io/badge/coverage-91%25-brightgreen)


## Sorting & Pagination (Planned)

Currently the API returns all workations in one response (sufficient for the take-home dataset).  
Next steps for production readiness:

- **Server-side sorting**
    - Support query params like:
        - `GET /workflex/workation?sort=startDate,asc`
        - `GET /workflex/workation?sort=risk,desc`
    - Implement using Spring Data `Sort` / `Pageable`.

- **Server-side pagination**
    - Support:
        - `GET /workflex/workation?page=0&size=20&sort=startDate,desc`
    - Return either Spring’s `Page<T>` metadata or a simple envelope:
        - `items`, `page`, `size`, `totalElements`, `totalPages`.

- **Stable filtering (optional)**
    - Add filters like `origin`, `destination`, `risk`, date ranges to reduce payload size.

## Future Improvements

- **Scalable data loading**
    - Replace “load entire CSV into memory” with a streaming approach (e.g., buffered reader / CSV parser streaming).
    - Batch inserts (e.g., `saveAll` in chunks) to reduce transaction size.
    - Make import idempotent using a unique constraint (`workation_id`) + upsert strategy (or ignore duplicates).

- **Production-ready API patterns**
    - Add server-side pagination + sorting (see above).
    - Add validation + consistent error responses for query params.
    - Add request-level caching headers where appropriate.

- **Observability**
    - Add basic request logging, latency metrics, and error counters.
    - Log import stats: rows processed, inserted, skipped, duration.

- **Database evolution**
    - Add Flyway/Liquibase migrations instead of relying on `ddl-auto`.