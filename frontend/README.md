# Frontend (Angular)

Angular frontend that displays Workations in a sortable table (flat grid
with black borders), including risk icons and country flags.

---

## Prerequisites

- Node.js + npm
- Angular CLI (optional if using npx)

---

## Run (Development)

### 1) Start the backend first

From the `backend/` folder:

```bash
./gradlew bootRun --args='--spring.profiles.active=dev'
```

Backend runs on:

http://localhost:8080

### 2) Start the frontend

From the `frontend/` folder:

```bash
npm install
npm start
```

or

```bash
ng serve
```

Frontend runs on:

http://localhost:4200

Open the Workations page:

http://localhost:4200/workflex/workations

---

## API Configuration

The frontend calls the backend directly using an environment-based base
URL.

### Environment files

- src/environments/environment.ts
- src/environments/environment.prod.ts

Example:

```ts
export const environment = {
  production: false,
  apiBaseUrl: 'http://localhost:8080',
};
```

The Workations API endpoint used:

GET {apiBaseUrl}/workflex/workation

---

## CORS (Required for Local Dev)

Because the frontend runs on http://localhost:4200 and backend runs on
http://localhost:8080, the backend must allow CORS for local
development.

The backend includes a CORS configuration that allows:

- Origin: http://localhost:4200
- Paths: /workflex/\*\*

---

## UI Features

- Flat table grid with black horizontal + vertical lines
- Frontend sorting (click column headers)
- Date format: dd/MM/yyyy
- Risk icons (SVG) for:
  - High risk (red)
  - Low risk (yellow)
  - No risk (green)
- Risk label rule:
  - HIGH -\> High risk
  - LOW and NO_RISK -\> No risk (different icons/colors)
- Country flags displayed next to Origin and Destination

---

## Assets

Risk icons are stored under:

- src/assets/icons/red-risk.svg
- src/assets/icons/yellow-risk.svg
- src/assets/icons/green-risk.svg

---

## Tests

Run unit tests:

```bash
ng test
```

---

## Troubleshooting

### Backend reachable but table is empty

- Confirm backend endpoint works:
  http://localhost:8080/workflex/workation
- Confirm CORS is enabled on backend for http://localhost:4200

### Risk icons not showing

Open directly in browser to confirm asset path:

http://localhost:4200/assets/icons/red-risk.svg
