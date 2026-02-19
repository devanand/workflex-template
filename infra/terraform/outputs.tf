output "db_endpoint" {
  value = aws_db_instance.postgres.address
}

output "db_port" {
  value = aws_db_instance.postgres.port
}

output "db_name" {
  value = aws_db_instance.postgres.db_name
}

output "db_username" {
  value = var.db_username
}

output "db_secret_arn" {
  value = aws_secretsmanager_secret.db.arn
}