import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LocalStorageService {
  private readonly AAE_SEARCHFORM = "aaeSearchForm";

  //private localStorage: Storage;

  constructor() {
    //this.localStorage = window.localStorage;
  }
  get(key: string): any {
    if (this.isLocalStorageSupported && localStorage.getItem(key)) {
      return JSON.parse(localStorage.getItem(key));
    }
    return null;
  }
  set(key: string, value: any): boolean {
    if (this.isLocalStorageSupported) {
      localStorage.setItem(key, JSON.stringify(value));
      return true;
    }
    return false;
  }
  remove(key: string): boolean {
    if (this.isLocalStorageSupported) {
      localStorage.removeItem(key);
      return true;
    }
    return false;
  }
  get isLocalStorageSupported(): boolean {
    return !!localStorage
  }


//methods to get, set and remove
getAaeSearchData(): any{
  return this.get(this.AAE_SEARCHFORM);
}

saveAaeSearchData(value: string){
  console.log('saving aaeSearch data to local storage: ' + value);
  this.set(this.AAE_SEARCHFORM, value);
}

removeAaeSearchData(){
  this.remove(this.AAE_SEARCHFORM);
}

getUsername(){
  return this.get("currentUser").principal.username;
}
}
