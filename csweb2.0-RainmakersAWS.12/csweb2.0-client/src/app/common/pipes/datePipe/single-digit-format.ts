import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'singleDigitFormat',
})
export class SingleDigitFormat implements PipeTransform {
  transform(value: string): string {
    return (value === '0' || value.trim() === "" || value === "0000000000") ? '00/00/0000' : value;
  }
}
