import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dateFormat',
})
export class DateFormatPipe implements PipeTransform {


  transform(value: string): string{
      if(value && value.length === 8){
        const year = value.slice(0,4);
        const month = value.slice(4,6);
        const day = value.slice(6,8);
        return `${month}/${day}/${year}`;
      }
      else{
        return '00/00/0000'
      }
    }

}
