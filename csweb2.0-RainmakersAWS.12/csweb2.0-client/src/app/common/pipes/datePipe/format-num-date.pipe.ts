import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'formatNumDate'
})
export class FormatNumDatePipe implements PipeTransform {

  transform(value: string): string {
    if (value === '0') {
        return '00/00/0000';
      }
  
      const match = value.match(/^(\d{4})(\d{2})(\d{2})$/);
      if (match) {
        return match[2] + '/' + match[3] + '/' + match[1];
      }
      return value;
    }
}  