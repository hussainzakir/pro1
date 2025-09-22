resource "aws_security_group" "alb"{
  name = "${var.app-name}-${var.env}-alb-sg"
  description = "access to things"
  vpc_id = var.vpcId[var.env_type]

  ingress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_alb" "main" {
  name               = "${var.app-name}-${var.env}-alb"
  internal           = true
  load_balancer_type = "application"
  security_groups    = [aws_security_group.alb.id]
  subnets            = var.subnets[var.env_type]

  enable_deletion_protection = false

}

resource "aws_alb_target_group" "main" {
  name        = "${var.app-name}-${var.env}-tg"
  port        = 8080
  protocol    = "HTTP"
  vpc_id      = var.vpcId[var.env_type]
  target_type = "ip"

  health_check {
    healthy_threshold   = "2"
    interval            = "61"
    protocol            = "HTTP"
    matcher             = "200"
    timeout             = "5"
    port                = "traffic-port"
    path                = "/ws/health"
    unhealthy_threshold = "5"
  }

  depends_on = [aws_alb.main]
}

resource "aws_alb_listener" "redirect" {
  load_balancer_arn = aws_alb.main.arn
  port              = 80
  protocol          = "HTTP"

  default_action {
    type = "redirect"

    redirect {
      port        = "443"
      protocol    = "HTTPS"
      status_code = "HTTP_301"
    }
  }
}

resource "aws_alb_listener" "https" {
  load_balancer_arn = aws_alb.main.arn
  port              = "443"
  protocol          = "HTTPS"
  ssl_policy        = "ELBSecurityPolicy-TLS-1-2-2017-01"
  certificate_arn   = aws_acm_certificate.cert.arn

  default_action {
    type             = "forward"
    target_group_arn = aws_alb_target_group.main.arn
  }
}

data "aws_route53_zone" "existing_zone" {
  name         = "${var.app-name}${var.dns_suffix[var.env_type]}.awsext.repsrv.com"
  private_zone = false
  depends_on = [aws_route53_zone.cswebzone]
}

resource "aws_route53_record" "record_csweb_dns" {
  type    = "CNAME"
  name    = var.env
  ttl     = "1800"
  zone_id = var.hosted_zone_create==0 ? data.aws_route53_zone.existing_zone.zone_id : aws_route53_zone.cswebzone[0].zone_id
  records = [aws_alb.main.dns_name]
}


## DO NOT DELETE OR MODIFY - MAIN ZONE FOR CSWEB
resource "aws_route53_zone" "cswebzone" {
  count = var.hosted_zone_create
  name = "${var.app-name}${var.dns_suffix[var.env_type]}.awsext.repsrv.com"

  lifecycle {
    ignore_changes = [name]
    prevent_destroy = true
  }
}


resource "aws_acm_certificate" "cert" {
  domain_name = "${var.env}.${var.app-name}${var.dns_suffix[var.env_type]}.awsext.repsrv.com"
  depends_on = [aws_route53_zone.cswebzone]
  validation_method = "DNS"

  lifecycle {
    create_before_destroy = true
  }
}

resource "aws_route53_record" "validation_records" {
#   count = var.hosted_zone_create
#   name = aws_acm_certificate.cert[0].domain_validation_options[count.index].resource_record_name
#   type  = aws_acm_certificate.cert[0].domain_validation_options[count.index].resource_record_type
#   zone_id = aws_route53_zone.cswebzone[count.index].zone_id
#   records = [aws_acm_certificate.cert[0].domain_validation_options[count.index].resource_record_value]
#   ttl = 60
#   allow_overwrite = true

  for_each = {
    for dvo in aws_acm_certificate.cert.domain_validation_options : dvo.domain_name => {
      name   = dvo.resource_record_name
      record = dvo.resource_record_value
      type   = dvo.resource_record_type
    }
  }
  name            = each.value.name
  records         = [each.value.record]
  ttl             = 60
  type            = each.value.type
  zone_id         = var.hosted_zone_create==0 ? data.aws_route53_zone.existing_zone.zone_id : aws_route53_zone.cswebzone[0].zone_id
}

resource "aws_acm_certificate_validation" "csweb-domain-validation" {
  count = var.hosted_zone_create
  certificate_arn         = aws_acm_certificate.cert.arn
  validation_record_fqdns = [for record in aws_route53_record.validation_records : record.fqdn]
}
