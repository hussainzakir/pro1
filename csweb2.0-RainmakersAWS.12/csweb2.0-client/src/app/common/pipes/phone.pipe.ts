import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'phonePipe'
})
export class PhonePipe implements PipeTransform {

  transform(phone: string): string{
    if(!phone || phone.trim() === '' ){
      return '(000) 000-0000';
    }
    else if(phone==='(   )    -'){
      return '(000) 000-0000';
    }
    else {
      return phone;
    }
  }

}
