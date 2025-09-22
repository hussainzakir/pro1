terraform{
  required_version = "~> 1.9.6"

   backend "remote" {}

  required_providers{
    aws = {
      source = "hashicorp/aws"
      version = ">= 4.22.0"
    }
  }
}
provider "aws" {
  region = var.aws_region
}

