
resource "aws_cloudwatch_log_group" "ecs_logs" {
  name              = "${var.app-name}-${var.env}-main"
  retention_in_days = 30
  tags              = var.common_tags
}



resource "aws_ecs_cluster" "csweb_app_cluster" {
  name = "${var.app-name}-${var.env}-ecs-cluster"

  setting {
    name = "containerInsights"
    value = "enabled"
  }
  tags = var.common_tags
}


resource "aws_ecs_task_definition" "csweb2_app_runner" {
  family                   = "${var.app-name}-${var.env}-task-def"
  requires_compatibilities = ["FARGATE"]
  network_mode             = "awsvpc"
  cpu                      = "2048"
  memory                   = "8192"
  execution_role_arn       = aws_iam_role.ecs_task_execution_role.arn
  task_role_arn = aws_iam_role.ecs_task_execution_role.arn
  container_definitions = jsonencode([
    {
      name   = "${var.app-name}-${var.env}-container"
      image  = "${module.csweb_app_repository.repository_url}:latest"
      cpu    = 2048
      memory = 8192
      portMappings = [
        {
          containerPort = 8080
          hostPort      = 8080
          protocol      = "tcp"
        }
      ]
      logConfiguration = {
        logDriver = "awslogs"
        options   = {
          "awslogs-group"         = aws_cloudwatch_log_group.ecs_logs.name
          "awslogs-region"        = var.aws_region
          "awslogs-stream-prefix" = "ecs"
        }
      }
      essential = true
      environment = [
        { name : "CSWEB_ENV", value : var.env },
        { name : "CSWEB_REGION", value : var.aws_region }
      ]
    }
  ])
  tags = var.common_tags
}

resource "aws_ecs_service" "csweb_app_service" {
  name            = "${var.app-name}-${var.env}-ecs-service"
  cluster         = aws_ecs_cluster.csweb_app_cluster.id
  task_definition = aws_ecs_task_definition.csweb2_app_runner.arn
  launch_type     = "FARGATE"
  desired_count   = 1
  propagate_tags = "SERVICE"
  deployment_maximum_percent = 200
  deployment_minimum_healthy_percent = 100

  network_configuration {
    subnets          = var.subnets[var.env_type]
    security_groups  = [module.web_access_sg.security_group_id]
    assign_public_ip = true
  }

  load_balancer {
    target_group_arn = aws_alb_target_group.main.arn
    container_name = "${var.app-name}-${var.env}-container"
    container_port = 8080
  }

  lifecycle {
    ignore_changes = [
      desired_count]
  }

  tags = var.common_tags
}

resource "aws_appautoscaling_target" "scaling_target" {
  max_capacity = 5
  min_capacity = 1
  resource_id = "service/${aws_ecs_cluster.csweb_app_cluster.name}/${aws_ecs_service.csweb_app_service.name}"
  scalable_dimension = "ecs:service:DesiredCount"
  service_namespace = "ecs"
}

resource "aws_appautoscaling_policy" "csweb_avg_memory_policy" {
  name               = "${var.app-name}-${var.env}-avg-memory-policy"
  policy_type        = "TargetTrackingScaling"
  resource_id        = aws_appautoscaling_target.scaling_target.resource_id
  scalable_dimension = aws_appautoscaling_target.scaling_target.scalable_dimension
  service_namespace  = aws_appautoscaling_target.scaling_target.service_namespace

  target_tracking_scaling_policy_configuration {
    predefined_metric_specification {
      predefined_metric_type = "ECSServiceAverageMemoryUtilization"
    }
    target_value       = 80
  }
}

resource "aws_appautoscaling_policy" "dev_to_cpu" {
  name = "${var.app-name}-${var.env}-avg-cpu-utilization-policy"
  policy_type = "TargetTrackingScaling"
  resource_id = aws_appautoscaling_target.scaling_target.resource_id
  scalable_dimension = aws_appautoscaling_target.scaling_target.scalable_dimension
  service_namespace = aws_appautoscaling_target.scaling_target.service_namespace

  target_tracking_scaling_policy_configuration {
    predefined_metric_specification {
      predefined_metric_type = "ECSServiceAverageCPUUtilization"
    }

    target_value = 65
  }
}
