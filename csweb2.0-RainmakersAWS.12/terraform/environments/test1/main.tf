module "csweb_modules" {
  source = "../../csweb-module"
  env = "test1"
  env_type = "test"
  hosted_zone_create = 0
}