import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Observable, ReplaySubject, combineLatest } from 'rxjs';
import { first, map, startWith } from 'rxjs/operators';

@Component({
  selector: 'app-auto-complete-dropdown',
  templateUrl: './auto-complete-dropdown.component.html',
  styleUrls: ['./auto-complete-dropdown.component.css']
})
export class AutoCompleteDropdownComponent implements OnInit {

  @Input("dropdownOptions1") dropdownOptions1: ReplaySubject<DropdownItem[]>;
  @Input("menuLabel") menuLabel: String;
  @Output() selectValueEmiter: EventEmitter<DropdownItem> = new EventEmitter<DropdownItem>();
  @Input("currentFormValue") currentFormValue: ReplaySubject<string>;
  @Input() readonly: boolean;

  myControl = new FormControl('');
  options: DropdownItem[] = [];
  filteredOptions: Observable<DropdownItem[]>;

  ngOnChanges(changes: SimpleChanges) {
    if (changes.readonly) {
      if (this.readonly) {
        this.myControl.disable();
      } else {
        this.myControl.enable();
      }
    }
  }

  ngOnInit() {
    this.dropdownOptions1.subscribe(data => {
      this.options = data;
    })
    this.filteredOptions = combineLatest([
      this.myControl.valueChanges.pipe(startWith('')),    
      this.dropdownOptions1.pipe(first()) // Take the first emitted value 
          ]).pipe(map(([value, newOptions]) => {
            this.selectValueEmiter.emit(newOptions.find(option => option.description == value));
            console.log(this.dropdownOptions1);
           return this._filter(value || '', newOptions);
          }
           ));
           this.currentFormValue.subscribe(data => {
            this.myControl.setValue(data);
            if (this.readonly) {
              this.myControl.disable();
            } else {
              this.myControl.enable();
            }
            console.log(this.myControl.value)
          });
}
  private _filter(value: string, newOptions: DropdownItem[]): DropdownItem[] {
    const filterValue = value.toLowerCase();
    console.log(newOptions);
    return newOptions.filter(option => option.description.toLowerCase().includes(filterValue));
    }
}

export type DropdownItem = {
  description: string;
  value: string;
  containerType ?: string;
  containerSize ?: string;
  compactorFlag ?: string;
}

