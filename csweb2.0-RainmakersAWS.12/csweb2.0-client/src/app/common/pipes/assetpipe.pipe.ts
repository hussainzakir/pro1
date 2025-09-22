import { Pipe, PipeTransform, Inject } from '@angular/core';
import { APP_BASE_HREF } from "@angular/common";

@Pipe({
  name: 'assetpipe'
})
export class AssetpipePipe implements PipeTransform {

  baseUrl: string;
      constructor(@Inject(APP_BASE_HREF) baseHref: string) {
        this.baseUrl = baseHref;
      }

      transform(value: string) {
        const newval = this.baseUrl + value;
           return newval;
      }

}
