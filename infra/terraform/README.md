# Infrastructure (Terraform)

This folder contains optional infrastructure-as-code definitions for provisioning cloud resources required for running the backend in a production-like environment.

Infrastructure is intentionally kept separate from the core application logic and is not required for local development.

---

## Scope

The current Terraform setup demonstrates:

- AWS provider configuration
- PostgreSQL database provisioning (RDS)
- Security group configuration
- Secrets management for database credentials

This infrastructure supports backend deployment in a cloud environment but is not required to complete the coding challenge.

---

## Local Development

Infrastructure is **not required** for running the application locally.

Local setup uses:

- H2 in-memory database
- Spring `dev` profile

See:

- `backend/README.md`
- `frontend/README.md`

---

## Usage (Optional)

From the `infra/terraform` directory:

```bash
terraform init
terraform plan
terraform apply
```

To destroy resources:

```bash
terraform destroy
```

---

## Notes

- Production deployment strategy is intentionally out of scope for this challenge.
- Infrastructure code is provided as a foundation for future extension.
- Credentials should be managed via environment variables or a secrets manager.
