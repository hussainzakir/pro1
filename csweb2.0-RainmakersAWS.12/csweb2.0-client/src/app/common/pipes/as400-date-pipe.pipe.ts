import { Pipe, PipeTransform } from '@angular/core';
import { formatDate} from '@angular/common';

@Pipe({
  name: 'as400DateToString'
})
export class As400DatePipePipe implements PipeTransform {

  transform(value: string): string {

    const [year, month, day, timeString="none"] = value.split('-');

    if(timeString != "none"){
      const [hours, minutes, seconds] = timeString.split('.') ;
      const date = new Date(+year, +month-1 , +day, +hours, +minutes, +seconds);
      const newFormat = formatDate(date, 'MM/dd/yyyy h:mm a', 'en_US')
      return newFormat;
    } else { //2022-05-01 21:19:44.562000
      const daySplit = day.split(' ')
      if(daySplit.length>1){
        const [hours, minutes, seconds] = daySplit[1].split('.') ;
        const date = new Date(+year, +month-1 , +daySplit[0], 0, 0, 0);
        const newFormat = formatDate(date, 'MM/dd/yyyy h:mm a', 'en_US');
        return newFormat;
      }else {
        const date = new Date(+year, +month-1 , +daySplit[0], 0, 0, 0);
        const newFormat = formatDate(date, 'MM/dd/yyyy', 'en_US');
        return newFormat;
      }

    }



  }

}
