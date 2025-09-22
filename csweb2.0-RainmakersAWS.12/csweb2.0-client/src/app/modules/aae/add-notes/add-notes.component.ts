import { AfterViewInit, Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef, MatSnackBar } from '@angular/material';
import { AaeSearchService } from '@app/core/services/aae-entry/aae-search.service';

@Component({
  selector: 'app-add-notes',
  templateUrl: './add-notes.component.html',
  styleUrls: ['./add-notes.component.css']
})
export class AddNotesComponent implements OnInit, AfterViewInit {

noteForm: FormGroup;
errorMsg: string;
@ViewChild('noteInput') noteInput;

  constructor(private builder:FormBuilder, private dialogRef: MatDialogRef<AddNotesComponent>,@Inject(MAT_DIALOG_DATA) public statusInfo: any, private searchService: AaeSearchService, private snackBar: MatSnackBar,) { }

  ngOnInit(): void {
    this.noteForm = this.builder.group({
      note: ['',Validators.maxLength(50)]
    });
  }

  ngAfterViewInit(): void {
    console.log(this.noteInput);
    this.noteInput.nativeElement.blur();
    this.noteInput.nativeElement.focus();
   
  }

submitForm(){
  console.log("list> ");
  console.log(this.statusInfo.list);
  this.searchService.updateStatusCode(this.statusInfo.statusCode, this.statusInfo.projectId, this.noteForm.controls.note.value)
      .subscribe( (response: any) => {
        if (response.previousStatusCode){
        this.showSnack("Quote Status has been updated.");
        this.statusInfo.list.forEach(record => {
          if(record.quoteID==this.statusInfo.quoteId){
            if(this.statusInfo.statusCode=='RLS' || this.statusInfo.statusCode=='UND'){
              record.statusCode = response.previousStatusCode;
            }else{
            record.statusCode=this.statusInfo.statusCode;
            }
            record.notesFound='Y';
            console.log(record.notesFound);
          } 
        });
        this.dialogRef.close();
      } else {
        this.showSnackWarning("Error updating the quote.");
      }
      
      },
      error => {
        this.showSnackWarning("Error updating the quote.")
       }
    );
}


showSnack(msg: string){
  this.snackBar.open("Success!  "+msg,null,
  {duration: 3.5 * 1000, panelClass: ['success-snackbar']});
}

showSnackWarning(msg: string){
  this.snackBar.open(msg,null,
  {duration: 5.5 * 1000, panelClass: ['blue-snackbar']});
}


}
