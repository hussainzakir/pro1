resource "aws_secretsmanager_secret" "csweb_secret" {
  name = "/secret/${var.env}/${var.app-name}"

}


resource "aws_secretsmanager_secret_version" "db_password_version"{
  secret_id = aws_secretsmanager_secret.csweb_secret.id
  secret_string = jsonencode(
    {
      "secrets.health.check": "success",
      "infopro.jdbc.username": "NOTSET",
      "infopro.jdbc.password": "NOTSET",
      "cbs.jdbc.username": "NOTSET",
      "cbs.jdbc.password": "NOTSET"
    })

  lifecycle {
    ignore_changes = [secret_string]
  }
}

resource "aws_ssm_parameter" "cbs_jdbc_host" {
  name = "/config/${var.env}/${var.app-name}/cbs/jdbc/host"
  type = "String"
  insecure_value = "{not set}"

  lifecycle {ignore_changes = [insecure_value]}
}

resource "aws_ssm_parameter" "infopro_jdbc_libs" {
  name = "/config/${var.env}/${var.app-name}/infopro/jdbc/libraries"
  type = "String"
  insecure_value = " "
  overwrite = true
 // lifecycle {ignore_changes = [insecure_value]}
}

resource "aws_ssm_parameter" "cbs_jdbc_libs" {
  name = "/config/${var.env}/${var.app-name}/cbs/jdbc/libraries"
  type = "String"
  insecure_value = "{not set}"

  lifecycle {ignore_changes = [insecure_value]}
}

resource "aws_ssm_parameter" "health_check" {
  name = "/config/${var.env}/${var.app-name}/health/check"
  type = "String"
  value = "success"
}

resource "aws_ssm_parameter" "infopro_jdbc_host" {
  name = "/config/${var.env}/${var.app-name}/infopro/jdbc/host"
  type = "String"
  insecure_value = "{not set}"

  lifecycle {ignore_changes = [insecure_value]}
}

resource "aws_ssm_parameter" "login_host" {
  name = "/config/${var.env}/${var.app-name}/login/host"
  type = "String"
  insecure_value = "{not set}"

  lifecycle {ignore_changes = [insecure_value]}
}

resource "aws_ssm_parameter" "mock_user" {
  name = "/config/${var.env}/${var.app-name}/mock/user"
  type = "String"
  insecure_value = "{not set}"

  lifecycle {ignore_changes = [insecure_value]}

}

resource "aws_ssm_parameter" "system_id" {
  name = "/config/${var.env}/${var.app-name}/system/id"
  type = "String"
  insecure_value = "{not set}"

  lifecycle {ignore_changes = [insecure_value]}
}

resource "aws_ssm_parameter" "s3_bucket" {
  name = "/config/${var.env}/${var.app-name}/s3bucket/name"
  type = "String"
  insecure_value = "${var.app-name}-${var.env}"
}

resource "aws_ssm_parameter" "aws_region" {
  name = "/config/${var.env}/${var.app-name}/aws/region"
  type = "String"
  insecure_value = data.aws_region.current.name
}