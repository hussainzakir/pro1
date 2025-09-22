import { FormGroup, AbstractControl} from '@angular/forms';

export interface TemplateUpload {
  template: string;
  changeNumber: string;
  uploadFile: string;
}

export interface TemplateUploadForm extends FormGroup {
    value: TemplateUpload;
    controls: {
      template: AbstractControl;
      changeNumber: AbstractControl;
      uploadFile: AbstractControl;
    };
}