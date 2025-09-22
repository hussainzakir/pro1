# Web Access Security Group
module "web_access_sg" {
  source      = "terraform-aws-modules/security-group/aws"
  version     = "5.1.0"
  name        = "${var.app-name}-${var.env}-web-acces-sg"
  description = "Security group for web access"
  vpc_id      = var.vpcId[var.env_type]

  ingress_cidr_blocks = ["10.0.0.0/8","172.16.0.0/12","192.168.0.0/16"]
  ingress_with_cidr_blocks = [
    {
      description = "Allow application access"
      from_port   = 443
      to_port     = 443
      protocol    = "tcp"
    },
    {
      description = "Allow application access to containers"
      from_port   = 8080
      to_port     = 8080
      protocol    = "tcp"
    }
  ]

  egress_cidr_blocks = ["0.0.0.0/0"]
  egress_with_cidr_blocks = [
    {
      from_port   = 0
      to_port     = 0
      protocol    = "-1"
    }
  ]

  tags = var.common_tags
}

