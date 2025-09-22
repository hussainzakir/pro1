import { Directive, HostListener } from '@angular/core';

@Directive({
  selector: '[appWindowbuttons]'
})
export class WindowbuttonsDirective {

  isMaximize: boolean = false;
 
  constructor() { }

  private restore (): void {
  const styleRestore = <HTMLScriptElement>document.querySelector(".cdk-overlay-container .cdk-global-overlay-wrapper .cdk-overlay-pane");
  const tableStyle1 = document.querySelector("#my-modal-dialog ") as HTMLElement;

  if(styleRestore){
    styleRestore.style.display = "block";
    styleRestore.style.maxHeight = "510px";
    styleRestore.style.width = "72%";
    styleRestore.style.height = "80%";
     styleRestore.style.position = "fixed";
    styleRestore.style.top = "10px"
  }

  if(tableStyle1){
    tableStyle1.style.height = "13rem";
  }

}

private maximize(): void {
  this.isMaximize = true;
  const styleMaximize = document.querySelector(".cdk-overlay-container .cdk-global-overlay-wrapper .cdk-overlay-pane") as HTMLElement;
  const tableStyle = document.querySelector("#my-modal-dialog ") as HTMLElement;

  if (styleMaximize) {
    styleMaximize.style.maxWidth = "95vw";
    styleMaximize.style.width = "95vw";
    styleMaximize.style.height = "96vh";
    styleMaximize.style.maxHeight = "96vh";
  }

  if (tableStyle) {
    tableStyle.style.height = "41.5rem";
  }
}


}
