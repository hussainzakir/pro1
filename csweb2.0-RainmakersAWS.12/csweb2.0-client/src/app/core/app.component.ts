import { BuildServiceService } from './services/build/build-service.service';
import { Component, OnInit, Inject, HostListener } from '@angular/core';
import { APP_BASE_HREF } from "@angular/common";
import { AuthGuard } from './guards/auth.guard';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'csweb2-client';
  build: string;
     constructor(@Inject(APP_BASE_HREF) private baseHref: string, private buildService: BuildServiceService, private authGuard: AuthGuard) {
       this.buildService.getBuildId().subscribe(
        (data: any) => { console.log(`Build Id: ${data}`);});
        console.log(`APP_BASE_HREF is ${this.baseHref}`);

    }

    ngOnInit(): void {
      this.authGuard.updateLastActivityTime();
    }
    @HostListener('document:mousemove')
    @HostListener('document:keypress')
    @HostListener('document:click')
    updateLastActivityTime(): void {
      this.authGuard.updateLastActivityTime();
    }
}
