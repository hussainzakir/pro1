variable "app-name" {default = "csweb2"}
variable "aws_region" {default = "us-east-2"}

variable "env" {
  description = "environment id ( dev1, dev2, test1, etc)"
}

variable "env_type" {
  description = "Is this a dev, test or prod account"
}

variable "hosted_zone_create" {
  description = "1 for TF to create and maintain. This should only be 1 for prod, test1 and dev1.  All others should be 0"
  default = 0
}

variable "common_tags" {
  type = map(string)
  default = {
    app = "csweb2"
  }
}

variable "aws_accounts" {
  type = map(string)
  default = {
    dev = "445567070276"
    test = "445567070276"
    prod = "084375590042"
  }
}

variable "dns_suffix" {
  type = map(string)

  default = {
    dev = "-nonprod"
    test = "-nonprod"
    prod = ""
  }
}

variable "subnets" {
  type = map(list(string))

  default = {
    dev = ["subnet-0f7a0357a3bb0416f","subnet-00ccfb918487d4946","subnet-0e3bf7d9e0de5c84f"]
    test = ["subnet-0f7a0357a3bb0416f","subnet-00ccfb918487d4946","subnet-0e3bf7d9e0de5c84f"]
    prod = ["subnet-06b091140e0f46c96","subnet-0a83d6aac0b5890f0","subnet-00428cd152e6cc276"]
  }
}

variable "vpcId" {
  type = map(string)

  default = {
    dev = "vpc-0626367bae828689e"
    test = "vpc-0626367bae828689e"
    prod = "vpc-0171383f55001303f"
  }
}

data "aws_region" "current" {}


