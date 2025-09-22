import { environment } from '../../../environments/environment';
import { HttpParameterCodec, HttpUrlEncodingCodec } from '@angular/common/http';

export class HttpService {

  protected json_headers = { 'content-type': 'application/json'};
  protected form_headers = { 'content-type': 'application/x-www-form-urlencoded'};

  formatUrl(url: string){
    if(environment.develop) {
      return `${environment.ws_url}${url}`;
    }

    return `${environment.api_root}${url}`
  }


}

export class CustomQueryEncoderHelper extends HttpUrlEncodingCodec {

  encodeKey(k: string): string {
      k = super.encodeKey(k);
      return k.replace(/\+/gi, '%2B')
      .replace(/\=/gi, '%3D');

  }
  encodeValue(v: string): string {
      v = super.encodeValue(v);
      return v.replace(/\+/gi, '%2B')
      .replace(/\=/gi, '%3D');
  }
}
