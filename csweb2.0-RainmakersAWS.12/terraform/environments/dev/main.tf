module "csweb_modules" {
  source = "../../csweb-module"
  env = "dev1"
  env_type = "dev"
  hosted_zone_create = 1
}

