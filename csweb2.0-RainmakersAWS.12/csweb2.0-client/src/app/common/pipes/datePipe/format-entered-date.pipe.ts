import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'formatEnteredDate'
})
export class FormatEnteredDatePipe implements PipeTransform {

  transform(value: string): string {
    const match = value.match(/^(\d{2})(\d{2})(\d{4})$/);
    return match ? match[1] + '/' + match[2] + '/' + match[3] : value;
  }

}