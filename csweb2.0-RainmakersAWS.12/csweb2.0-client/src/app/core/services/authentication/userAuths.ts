import { Injectable } from "@angular/core";
import { LocalStorageService } from "../LocalStorage.service";

@Injectable({
    providedIn: 'root'
  })

export class UserAuths {
   constructor(private localStorage: LocalStorageService){}

    setSuperPerms(perms: any){
      this.localStorage.set("superPerms", JSON.stringify(perms));
    }

    getSuperPerms(){
      return JSON.parse(this.localStorage.get("superPerms"));
    }

    setAuths(authorities: String[]){
        this.localStorage.set("authorities", JSON.stringify(authorities));
    }
    getAuths(){
        return JSON.parse(this.localStorage.get("authorities"));
    }
    removeAuths(){
        this.localStorage.remove("authorities");
    }

    isSuperUser(){
      let permObj = this.getSuperPerms();
      return 'Y'== permObj?.superUser;


    }
}
