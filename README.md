# WorkFlex Template (Monorepo)

This repository is a lightweight full-stack template intended for a timed coding challenge setup. It provides:
- A Spring Boot backend (Java 21 / Spring Boot 4) with H2 dev database, OpenAPI docs, Docker support, and clean layering.
- An Angular frontend (Angular CLI) with a dev proxy to the backend.
- An optional infrastructure folder (Terraform) for future deployment work.

---

## **Repository Structure**

- `backend/` – Spring Boot API  
- `frontend/` – Angular app  
- `infra/` – Infrastructure (Terraform)

### Component Documentation

- [Backend README](backend/README.md)
- [Frontend README](frontend/README.md)
- [Infrastructure README](infra/terraform/README.md)

---

## **Features**

- Full-stack local development (Angular + Spring Boot)
- RESTful CRUD API (sample) with layered architecture
- Input validation and centralized error handling (`ProblemDetail`)
- OpenAPI documentation (Swagger UI)
- H2 dev database + H2 console
- Dockerized backend runtime
- Checkstyle + JaCoCo integration
- Frontend dev proxy to avoid CORS during local development

---

## **Overall Architecture**

### Backend (Spring Boot)

Layered structure:
- `api` – Controllers & DTOs
- `service` – Business logic
- `repository` – Data access
- `domain` – Entities
- `exception` – Application-level exceptions

Centralized exception handling ensures consistent HTTP responses.

### Frontend (Angular)

- Feature-oriented structure (`core`, `features`, `shared`)
- Backend calls go through `/api/...` and are proxied to `http://localhost:8080` in dev

---

## **API Endpoints (Sample)**

Base path:
- `/api`

Sample resource:
- `POST /api/items`
- `GET /api/items`
- `GET /api/items/{id}`
- `PUT /api/items/{id}`
- `DELETE /api/items/{id}`

Health:
- `GET /actuator/health`

Docs:
- Swagger UI: `/docs`

---

## **Run Locally (Full Stack)**

### 1) Start Backend

```bash
cd backend
./gradlew bootRun --args='--spring.profiles.active=dev'
```

### 2) Start Frontend

```bash
cd frontend
npm install
npm start
```

Frontend: http://localhost:4200  
Backend: http://localhost:8080

---

## **Notes**

- Production deployment is intentionally out of scope for the challenge template.
- See individual component READMEs for detailed setup and execution instructions.
