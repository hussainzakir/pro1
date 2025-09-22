module "csweb_modules" {
  source = "../../csweb-module"
  env = "prod"
  env_type = "prod"
  hosted_zone_create = 1
}