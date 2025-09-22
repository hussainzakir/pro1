locals {
  ecr_name = "${var.app-name}-${var.env}-repository"
}

module "csweb_app_repository" {
  source  = "terraform-aws-modules/ecr/aws"
  version = "1.6.0"

  repository_name                 = local.ecr_name
  repository_type                 = "private"
  repository_image_tag_mutability = "MUTABLE"
  create_lifecycle_policy         = true
  repository_force_delete = true

  # only keep the latest 10 images
  repository_lifecycle_policy = jsonencode({
    rules = [
      {
        rulePriority = 1
        description  = "Expire images by count"
        selection = {
          tagStatus   = "any"
          countType   = "imageCountMoreThan"
          countNumber = 10
        }

        action = {
          type = "expire"
        }
      }
    ]
  })

  tags = merge(var.common_tags)
}

resource "aws_ecr_repository_policy" "app" {
  repository = local.ecr_name
  policy     = data.aws_iam_policy_document.ecr.json
}

data "aws_iam_policy_document" "ecr" {
  statement {
    actions = [
      "ecr:GetDownloadUrlForLayer",
      "ecr:BatchGetImage",
      "ecr:BatchCheckLayerAvailability",
      "ecr:PutImage",
      "ecr:InitiateLayerUpload",
      "ecr:UploadLayerPart",
      "ecr:CompleteLayerUpload",
      "ecr:DescribeRepositories",
      "ecr:GetRepositoryPolicy",
      "ecr:ListImages",
      "ecr:DescribeImages",
      "ecr:DeleteRepository",
      "ecr:BatchDeleteImage",
      "ecr:SetRepositoryPolicy",
      "ecr:DeleteRepositoryPolicy",
      "ecr:GetLifecyclePolicy",
      "ecr:PutLifecyclePolicy",
      "ecr:DeleteLifecyclePolicy",
      "ecr:GetLifecyclePolicyPreview",
      "ecr:StartLifecyclePolicyPreview",
    ]

    principals {
      type = "AWS"

      identifiers = [
        "arn:aws:iam::${var.aws_accounts[var.env_type]}:role/gh-oidc"
      ]
    }
  }
}