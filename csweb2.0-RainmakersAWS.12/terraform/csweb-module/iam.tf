# ECS Task execution role
resource "aws_iam_role" "ecs_task_execution_role" {
  name = "${var.app-name}-${var.env}-ecs-task-execution-role"
  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Action = "sts:AssumeRole"
        Effect = "Allow"
        Sid    = ""
        Principal = {
          Service = "ecs-tasks.amazonaws.com"
        }
      }
    ]
  })
  tags = var.common_tags
}

resource "aws_iam_policy" "task_ssm_policy" {
  name        = "${var.app-name}-${var.env}-task-ssm-policy"
  description = "Policy to allow the assume role to read SSM values"
  policy      = jsonencode(
    {
      "Version" : "2012-10-17",
      "Statement" : [
        {
          "Effect" : "Allow",
          "Action" : "*",
          "Resource" : [
            aws_secretsmanager_secret_version.db_password_version.arn
          ]
        },
        {
          "Effect" : "Allow",
          "Action" : [
            "ssm:Describe*",
            "ssm:Get*",
            "ssm:List*",
            "ssm:GetParametersByPath"
          ],
          "Resource" : "*"
        },
        {
          "Effect" : "Allow",
          "Action" : "secretsmanager:ListSecrets",
          "Resource" : "*"
        },
        {
          "Effect": "Allow",
          "Action": [
            "secretsmanager:GetResourcePolicy",
            "secretsmanager:GetSecretValue",
            "secretsmanager:DescribeSecret",
            "secretsmanager:ListSecretVersionIds"
          ],
          "Resource": "*"
        },
        {
          "Sid":"ReadWriteS3",
          "Action": [
            "s3:ListBucket"
          ],
          "Effect": "Allow",
          "Resource": ["arn:aws:s3:::${var.app-name}-${var.env}"]
        },
        {
          "Effect": "Allow",
          "Action": [
            "s3:PutObject",
            "s3:GetObject",
            "s3:GetObjectTagging",
            "s3:DeleteObject",
            "s3:DeleteObjectVersion",
            "s3:GetObjectVersion",
            "s3:GetObjectVersionTagging",
            "s3:GetObjectACL",
            "s3:PutObjectACL"
          ],
          "Resource": ["arn:aws:s3:::${var.app-name}-${var.env}/*"]
        }
      ]
    })
}

resource "aws_iam_role_policy_attachment" "task_ssm_policy_attachment" {
  policy_arn = aws_iam_policy.task_ssm_policy.arn
  role = aws_iam_role.ecs_task_execution_role.name
}

resource "aws_iam_role_policy_attachment" "ecs_task_execution_role_policy_attach" {
  policy_arn = "arn:aws:iam::aws:policy/service-role/AmazonECSTaskExecutionRolePolicy"
  role = aws_iam_role.ecs_task_execution_role.name
}

# Github action IAM User
# resource "aws_iam_user" "github_actions_user" {
#   name = "${var.app-name}-${var.env}-github-actions-user"
#   tags = var.common_tags
#
# }

# resource "aws_iam_access_key" "github_actions_user_access_key" {
#   user = aws_iam_user.github_actions_user.name
# }

resource "aws_iam_role" "github_actions_user_access_policy" {
  name = "${var.app-name}-${var.env}-github-actions-user-access-policy"

  depends_on = [
    module.csweb_app_repository,
    aws_ecs_service.csweb_app_service,
    aws_iam_role.ecs_task_execution_role
  ]


  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        "Effect": "Allow",
        "Principal": {
          "Federated": "arn:aws:iam:::oidc-provider/token.actions.githubusercontent.com"
        },
        "Action": "sts:AssumeRoleWithWebIdentity",
        "Condition": {
          "StringEquals": {
            "token.actions.githubusercontent.com:aud": "sts.amazonaws.com",
            "token.actions.githubusercontent.com:sub": "repo:RepublicServicesRepository/csweb2.0:ref:refs/heads/develop"
          }
        }
      }
    ]
  })

}

resource "aws_iam_role_policy_attachment" "github_actions_user_policy_attach" {
  policy_arn = "arn:aws:iam::aws:policy/AmazonEC2ContainerRegistryFullAccess"
  role = aws_iam_role.github_actions_user_access_policy.name
}