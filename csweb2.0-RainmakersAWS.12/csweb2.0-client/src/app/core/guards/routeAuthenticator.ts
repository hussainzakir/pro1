import { Injectable } from "@angular/core";
import { UserAuths } from "../services/authentication/userAuths";

@Injectable({
    providedIn: 'root'
  })

export class RouteAuthenticator {

  constructor(private userAuths: UserAuths){}

    validations: Validation[] = [
        (url: string, permissions: String[]) => url === "/price-increase-upload" && permissions.includes("PRICE_UPLOAD_ACCESS"),
        (url: string, permissions: String[]) => url === "/mass-uploads".trim() && permissions.includes("MASS_UPLOADS_ACCESS"),
        (url: string, permissions: String[]) => url.startsWith("/aae") && permissions.includes("AAE_ACCESS"),
        (url: string, permissions: String[]) => url === "/dashboard",
        (url: string, permissions: String[]) => url.startsWith("/cs-search"),
        (url: string, permissions: String[]) => url.startsWith("/audit"),
        (url: string, permissions: String[]) => url.startsWith("/account-imports") && (permissions.includes("AOB_MENU_ACCESS") || permissions.includes("AAI_MENU_ACCESS")),
        (url: string, permissions: String[]) => url.startsWith("/account"),
        (url: string, permissions: String[]) => url.startsWith("/imports"),
        (url: string, permissions: String[]) => url.startsWith("/projectdetails")
    ]

 validateAuths(url: String){
   return  this.validations.find(validation => validation(url, this.userAuths.getAuths())) !== undefined;
 }
}

export type Validation = (url: String, permissions: String[]) => boolean;
