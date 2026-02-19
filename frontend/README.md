# Frontend – WorkFlex Coding Challenge

## Tech Stack

Angular (CLI) · TypeScript · SCSS · npm

---

## Run Locally

From the `frontend/` folder:

```bash
npm install
npm start
```

App runs at:

- http://localhost:4200

---

## Backend Integration (Dev Proxy)

This project uses an Angular dev-server proxy so the frontend can call the backend without CORS issues.

- Frontend calls: `/api/...`
- Proxy forwards to: `http://localhost:8080/api/...`

Config file:

- `proxy.conf.json`

> Ensure the backend is running on `http://localhost:8080`.

Quick check:

- Open http://localhost:4200/ and verify it loads ping data from the backend.

---

## Project Structure

- `src/app/core` – API services, shared infra (http, interceptors)
- `src/app/features` – Feature pages/components (e.g., ping)
- `src/app/shared` – Reusable UI components (optional)

---

## Useful Commands

```bash
npm test
npm run build
```
