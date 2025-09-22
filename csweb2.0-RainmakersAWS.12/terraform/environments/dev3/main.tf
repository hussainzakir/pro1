module "csweb_modules" {
  source = "../../csweb-module"
  env = "dev3"
  env_type = "dev"
  hosted_zone_create = 0
}