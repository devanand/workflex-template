variable "aws_region" {
  type    = string
  default = "eu-central-1"
}

variable "db_name" {
  type    = string
  default = "workflex"
}

variable "db_username" {
  type    = string
  default = "app"
}

variable "db_instance_class" {
  type    = string
  default = "db.t4g.micro"
}

variable "db_allocated_storage_gb" {
  type    = number
  default = 20
}

variable "allowed_cidr_blocks" {
  description = "Who can reach Postgres (5432). Set to your public IP /32 for safety."
  type        = list(string)
  default     = []
}