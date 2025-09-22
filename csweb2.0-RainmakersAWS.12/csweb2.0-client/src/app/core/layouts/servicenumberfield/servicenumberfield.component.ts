import { FocusMonitor } from '@angular/cdk/a11y';
import { Component, ElementRef, forwardRef, Input } from '@angular/core';
import { FormGroup, FormBuilder, NgControl, NG_VALUE_ACCESSOR, ControlValueAccessor, AbstractControl } from '@angular/forms';
import { MatFormFieldControl } from '@angular/material/form-field';
import { Subject } from 'rxjs';

export class AccountNumberParts {
  constructor(public division: string, public serviceAccount: string, public site: string, public grp: string) { }
}

@Component({
  selector: 'app-servicenumberfield',
  templateUrl: './servicenumberfield.component.html',
  styleUrls: ['./servicenumberfield.component.css'],
  providers: [{ provide: MatFormFieldControl, useExisting: ServicenumberfieldComponent },
  {
    provide: NG_VALUE_ACCESSOR,
    useExisting: forwardRef(() => ServicenumberfieldComponent),
    multi: true
  }
  ],
  host: {
    '[class.example-floating]': 'shouldLabelFloat',
    '[id]': 'id',
  },
})
export class ServicenumberfieldComponent implements MatFormFieldControl<AccountNumberParts>, ControlValueAccessor {
  private innerValue: any;
  static nextId = 0;

  @Input() placeholder: string;
  @Input() required: boolean;

  focused = false;
  errorState = false;
  controlType = 'app-servicenumberfield';
  id = `app-servicenumberfield-${ServicenumberfieldComponent.nextId++}`;
  onChange = (_: any) => { };
  onTouched = () => { };
  myForm: FormGroup;
  stateChanges = new Subject<void>();
  touched = false;
  writeValue(tel: AccountNumberParts | null): void {
    this.value = tel;
  }

  get empty(): boolean {
    const { value: { division, serviceAccount, site } } = this.myForm;
    return !division && !serviceAccount && !site;
  }

  get shouldLabelFloat() {
    return this.focused || !this.empty;
  }

  get value(): AccountNumberParts | null {
    const { value: { division, serviceAccount, site, grp } } = this.myForm;
    if (division.length === 3 && serviceAccount.length === 3 && site.length === 4) {
      return new AccountNumberParts(division, serviceAccount, site, grp);
    }
    return null;
  }

  set value(tel: AccountNumberParts | null) {
    tel = tel || new AccountNumberParts('', '', '', '');
    this.myForm.setValue({ division: tel.division, serviceAccount: tel.serviceAccount, site: tel.site, grp: tel.grp });
    this.stateChanges.next();
  }

  constructor(
    fb: FormBuilder,
    private _focusMonitor: FocusMonitor,
    private _elementRef: ElementRef<HTMLElement>,
  ) {
    this.myForm = fb.group({
      'division': '',
      'serviceAccount': '',
      'site': '',
      'grp': ''
    });
    if (this.ngControl != null) { this.ngControl.valueAccessor = this; }
  }
  ngControl: NgControl;
  disabled: boolean;
  autofilled?: boolean;

  ngOnDestroy(): void {
    this.stateChanges.complete();
    this.onTouched();
  }

  onContainerClick(event: MouseEvent): void {
    if ((event.target as Element).tagName.toLowerCase() !== 'input') {
      this.myForm.controls['division'].markAsTouched();
      this.myForm.controls['serviceAccount'].markAsTouched();
      this.myForm.controls['site'].markAsTouched();
      this.myForm.controls['grp'].markAsTouched();
      this.focused = true;
      this.stateChanges.next();
    }
  }

  registerOnChange(fn: (_: any) => void): void {
    this.onChange = fn;
  }

  registerOnTouched(fn: () => void): void {
    this.onTouched = fn;
  }

  setDescribedByIds(ids: string[]): void { /* TODO document why this method 'setDescribedByIds' is empty */  /* TODO document why this method 'setDescribedByIds' is empty */ }

  setErrorState(errorState: boolean): void {
    this.errorState = errorState;
    this.stateChanges.next();
  }

  setDisabledState(isDisabled: boolean): void {
    isDisabled ? this.myForm.disable() : this.myForm.enable();
    this.stateChanges.next();
  }

  _handleInput(control: AbstractControl, nextElement?: HTMLInputElement): void {
    this.onChange(this.value);
  }
  skipToAccount(control: AbstractControl, nextElement?: HTMLInputElement): void {
    if(control.value.length ==4 )
    this.autoFocusNext(control, nextElement);
  }
  autoFocusNext(
    control: AbstractControl,
    nextElement?: HTMLInputElement
  ): void {
    if (!control.errors && nextElement) {
      this._focusMonitor.focusVia(nextElement, 'program');
    }
  }

  
  onFocusIn(event: FocusEvent) {
    if (!this.focused) {
      this.focused = true;
      this.stateChanges.next();
    }
  }

  onFocusOut(event: FocusEvent) {
    if (
      !this._elementRef.nativeElement.contains(event.relatedTarget as Element)
    ) {
      this.touched = true;
      this.focused = false;
      this.onTouched();
      this.stateChanges.next();
    }
  }
  autoFocusPrev(control: AbstractControl, prevElement: HTMLInputElement): void {
    if (control.value.length < 1) {
      this._focusMonitor.focusVia(prevElement, 'program');
    }
  }
}