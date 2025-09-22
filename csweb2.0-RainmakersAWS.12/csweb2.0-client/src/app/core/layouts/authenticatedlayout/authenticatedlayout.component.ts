import { Component, OnInit, OnDestroy, ChangeDetectorRef } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/authentication/auth.service';
import { MediaMatcher } from '@angular/cdk/layout';
import { MultilevelNodes, Configuration } from 'ng-material-multilevel-menu';
import { UserAuths } from '@app/core/services/authentication/userAuths';

@Component({
  selector: 'app-authenticatedlayout',
  templateUrl: './authenticatedlayout.component.html',
  styleUrls: ['./authenticatedlayout.component.css'],
})
export class AuthenticatedlayoutComponent implements OnInit, OnDestroy {
  mobileQuery: MediaQueryList;
  router: Router;

  config: Configuration = {
    paddingAtStart: true,
    classname: 'my-custom-class',
    listBackgroundColor: 'rgb(208, 241, 239)',
    fontColor: 'rgb(8, 54, 71)',
    backgroundColor: 'rgb(208, 241, 239)',
    selectedListFontColor: '#00adef',
    interfaceWithRoute: true,
  };

  appitems: MultilevelNodes[] = [
    // {
    //   label: 'AAE Templates',
    //   link: '/aae-templates',
    //   faIcon: 'fas fa-file-invoice',
    //   hidden: true,
    // },
    // {
    //   label: 'Account Onboarding',
    //   faIcon: 'fas fa-user-plus',
    //   items: [
    //     {
    //       label: 'Project Maintenance',
    //       link: '/account-onboarding/projects',
    //       faIcon: 'fas fa-tasks',
    //     },
    //     {
    //       label: 'Template Maintenance',
    //       link: '/account-onboarding/templates',
    //       faIcon: 'far fa-file-alt',
    //     },
    //   ],
    //   hidden: true,
    // },
    // {
    //   label: 'Audit',
    //   link: '/audit',
    //   faIcon: 'fas fa-business-time',
    //   hidden: true,
    // },
    // {
    //   label: 'Automated Account Entry (AAE)',
    //   link: '/aae',
    //   faIcon: 'fas fa-file-invoice',
    // },
    // {
    //   label: 'Search Options',
    //   faIcon: 'fas fa-user-plus',
    //   items: [
    //  {
    //       label: 'CS Search',
    //       link: '/cs-search',
    //       faIcon: 'fas fa-search',
    //     },
    //     {
    //       label: 'Obligations Search',
    //       link: '/obligations-search',
    //       faIcon: 'fas fa-search',
    //     }
    //   ]
    // },
    // {
    //         label: 'CS Search',
    //         link: '/cs-search',
    //         faIcon: 'fas fa-search',
    //       },
    //   {
    //     label: 'Account Imports',
    //     link: '/account-imports',
    //     faIcon: 'fas fa-address-card'
    //   },
    //   {
    //       label: 'Mass Uploads',
    //       link: '/mass-uploads',
    //       faIcon: 'fas fa-cloud-upload-alt',
    //       hidden: true
    //   },
    //   {
    //       label: 'Price Increase Upload',
    //       link: 'price-increase-upload',
    //       faIcon: 'fas fa-money-check-alt',
    //       hidden: false
    //   },
    //   {
    //     label: 'Audit',
    //     link: '/audit',
    //     faIcon: 'fas fa-business-time'
    //   }

  ];


  constructor(
    router: Router,
    private authService: AuthService,
    changeDetectorRef: ChangeDetectorRef,
    media: MediaMatcher,
    private userAuths: UserAuths
  ) {
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    this.mobileQuery.addListener(this._mobileQueryListener);
    this.router = router;
  }

  ngOnInit(): void {
    console.log(this.userAuths.getAuths());
    if (this.userAuths.getAuths().find(authority => authority.trim() === "AAE_ACCESS")){
    this.appitems.push({
      label: 'Automated Account Entry (AAE)',
      link: '/aae',
      faIcon: 'fas fa-file-invoice',
      hidden: false,
    });
    }
    if (this.userAuths.getAuths().find(authority => authority.trim() === "MASS_UPLOADS_ACCESS")){
    this.appitems.push({
        label: 'Mass Uploads',
        link: '/mass-uploads',
        faIcon: 'fas fa-cloud-upload-alt',
        hidden: false
    });
  }
    if (this.userAuths.getAuths().find(authority => authority.trim() === "PRICE_UPLOAD_ACCESS")){
      this.appitems.push( {
        label: 'Price Increase Upload',
        link: '/price-increase-upload',
        faIcon: 'fas fa-money-check-alt',
        hidden: false,
    });
  }
    this.appitems.push({
        label: 'Account Onboarding',
        faIcon: 'fas fa-user-plus',
        items: [
          {
            label: 'Project Maintenance',
            link: '/account-onboarding/projects',
            faIcon: 'fas fa-tasks',
          },
          {
            label: 'Template Maintenance',
            link: '/account-onboarding/templates',
            faIcon: 'far fa-file-alt',
          },
        ],
        hidden: true,
      });
      this.appitems.push({
        label: 'Audit',
        link: '/audit',
        faIcon: 'fas fa-business-time',
        hidden: true,
      });
      // {
      //   label: 'Search Options',
      //   faIcon: 'fas fa-user-plus',
      //   items: [
      //  {
      //       label: 'CS Search',
      //       link: '/cs-search',
      //       faIcon: 'fas fa-search',
      //     },
      //     {
      //       label: 'Obligations Search',
      //       link: '/obligations-search',
      //       faIcon: 'fas fa-search',
      //     }
      //   ]
      // },

      this.appitems.push({
              label: 'CS Search',
              link: '/cs-search',
              faIcon: 'fas fa-search',
        });

     if (this.userAuths.getAuths().find(authority => 
          authority.trim() === "AOB_MENU_ACCESS" || 
          authority.trim() === "AAI_MENU_ACCESS"
    )){
        this.appitems.push({
          label: 'Account Imports',
          link: '/account-imports',
          faIcon: 'fas fa-address-card'
        });
      }

        this.appitems.push({
          label: 'Audit',
          link: '/audit',
          faIcon: 'fas fa-business-time'
        });
        //  this.appitems.push({
        //   label: 'Account Imports 3.0',
        //   link: '/imports',
        //   faIcon: 'fas fa-address-card'
        // });
  }

  private _mobileQueryListener: () => void;

  selectedItem(evnt: any): void {
    console.log(evnt.link);
    if(evnt.link != "/audit"){
    localStorage.removeItem("auditFormValues");
    localStorage.removeItem("auditRequest");
    }
    //this.router.navigate(['massuploads']);
  }

  onLogout() {
    this.authService.logout().subscribe(
      (data) => {
        this.router.navigate(['/login']);
      },
      (err) => {
        console.log('logout failed ' + err);
      }
    );
  }

  ngOnDestroy(): void {
    this.mobileQuery.removeListener(this._mobileQueryListener);
  }
}
