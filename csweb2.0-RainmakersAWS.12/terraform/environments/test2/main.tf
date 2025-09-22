module "csweb_modules" {
  source = "../../csweb-module"
  env = "test2"
  env_type = "test"
  hosted_zone_create = 0
}