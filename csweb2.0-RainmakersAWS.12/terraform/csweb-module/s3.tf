resource "aws_s3_bucket" "my_bucket" {
  bucket  = "${var.app-name}-${var.env}"
  }