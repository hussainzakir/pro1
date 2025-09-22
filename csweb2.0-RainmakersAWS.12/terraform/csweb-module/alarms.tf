resource "aws_cloudwatch_metric_alarm" "ecs_cpu_high" {
  alarm_name          = "${var.app-name}-${var.env}-container-high-cpu-alarm"
  comparison_operator = "GreaterThanThreshold"
  evaluation_periods  = "3"
  metric_name         = "CpuUtilized"
  namespace           = "ECS/ContainerInsights"
  period              = "300"
  statistic           = "Average"
  threshold           = "80"
  alarm_description   = "This alarm fires when CPU usage is above 80%"
  alarm_actions       = [aws_sns_topic.alerts.arn]

  dimensions = {
    ClusterName = aws_ecs_cluster.csweb_app_cluster.name
    ServiceName = aws_ecs_service.csweb_app_service.name
  }
}

resource "aws_cloudwatch_metric_alarm" "ecs_memory_utilization_alarm" {
  alarm_name          = "${var.app-name}-${var.env}-container-memory-alarm"
  alarm_description   = "Alarm when container memory utilization exceeds 80%."

  comparison_operator       = "GreaterThanThreshold"
  evaluation_periods        = 2
  threshold                 = 80
  treat_missing_data        = "breaching"
  alarm_actions             = [aws_sns_topic.alerts.arn] # Optional
  datapoints_to_alarm       = 2


  metric_query {
    id          = "mem_used"
    metric {
      metric_name = "MemoryUtilized"
      namespace   = "ECS/ContainerInsights"
      period      = 300
      stat        = "Average"
      dimensions = {
        ClusterName = aws_ecs_cluster.csweb_app_cluster.name
        ServiceName = aws_ecs_service.csweb_app_service.name
      }
    }
    return_data = false
  }

  metric_query {
    id          = "mem_reserved"
    metric {
      metric_name = "MemoryReserved"
      namespace   = "ECS/ContainerInsights"
      period      = 300
      stat        = "Average"
      dimensions = {
        ClusterName = aws_ecs_cluster.csweb_app_cluster.name
        ServiceName = aws_ecs_service.csweb_app_service.name
      }
    }
    return_data = false
  }

  metric_query {
    id          = "mem_percent"
    expression  = "mem_used / mem_reserved * 100"
    label       = "Memory Usage %"
    return_data = true
  }

}

resource "aws_cloudwatch_metric_alarm" "ecs_low_running_tasks" {
  alarm_name          = "${var.app-name}-${var.env}-low-running-tasks"
  comparison_operator = "LessThanThreshold"
  evaluation_periods  = 2
  metric_name         = "RunningTaskCount"
  namespace           = "ECS/ContainerInsights"
  period              = 300
  statistic           = "Average"
  threshold           = aws_ecs_service.csweb_app_service.desired_count
  alarm_description   = "Alarm when running task count for ${aws_ecs_service.csweb_app_service.name} drops below desired count."
  treat_missing_data  = "breaching"
  alarm_actions       = [aws_sns_topic.alerts.arn] # Optional

  dimensions = {
    ClusterName = aws_ecs_cluster.csweb_app_cluster.name
    ServiceName = aws_ecs_service.csweb_app_service.name
  }
}

resource "aws_sns_topic" "alerts" {
  name = "ecs-container-alerts"
}

resource "aws_sns_topic_subscription" "email" {
  topic_arn = aws_sns_topic.alerts.arn
  protocol  = "email"
  endpoint  = "ASuft@republicservices.com"
}