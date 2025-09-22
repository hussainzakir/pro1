import { AccountImportComponent } from './../modules/account/import/aai/account-import.component';
import { NgModule } from '@angular/core';
import { AuthGuard } from './guards/auth.guard';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './authentication/login/login.component';
import { DashboardComponent } from '../modules/dashboard/dashboard.component';
import { AuthenticatedlayoutComponent } from './layouts/authenticatedlayout/authenticatedlayout.component';
import { AnonymouslayoutComponent } from './layouts/anonymouslayout/anonymouslayout.component';
import { MassUploadsComponent } from '../modules/mass-uploads/mass-uploads.component';
import { AaeTemplatesComponent } from '../modules/aae-templates/aae-templates.component';
import { AuditComponent } from '../modules/audit/audit.component';
import { PriceIncreaseUploadComponent } from '../modules/price-increase-upload/price-increase-upload.component';
import { CsSearchComponent } from '../modules/cs-search/cs-search.component';
import { AccountComponent } from '../modules/account/account.component'
import { AccountDetailComponent } from '../modules/account/account-detail/account-detail.component'
import { ObligationsSearchComponent } from '../modules/obligations-search/obligations-search.component';
import { AutomatedAccountEntryComponent } from '@app/modules/aae/entry/aae-entry.component';
import { AaeSearchComponent } from '@app/modules/aae/aae-search.component';
import { ContractInfoComponent } from '@app/modules/aae/entry/contract-info/contract-info/contract-info.component';
import { ContainerDetailComponent } from '@app/modules/site/container/container-detail/container-detail.component';
import { AccountImports3Component } from '@app/modules/account/imports3/account-imports3/account-imports3.component';
import { Aob3ProjectHistoryComponent } from '@app/modules/account/imports3/aob3-project-details/aob3-project-history/aob3-project-history.component';

const routes: Routes = [
  {
    path: '', component: AnonymouslayoutComponent, children: [
      { path: 'login', component: LoginComponent },
      {
        path: '',
        redirectTo: '/dashboard',
        pathMatch: 'full'
      }
    ]
  },
      {
        path: 'aae/quote/:quoteId/contractinfo',
        component: ContractInfoComponent,
        canActivate: [AuthGuard]
      },
  {
    path: '',
    component: AuthenticatedlayoutComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: 'mass-uploads',
        component: MassUploadsComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'account-imports',
        component: AccountImportComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'imports',
        component: AccountImports3Component,
        canActivate: [AuthGuard]
      },
      {
        path: 'projectdetails/:projectId',
        component: Aob3ProjectHistoryComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'aae-templates',
        component: AaeTemplatesComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'audit',
        component: AuditComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'automated-account-entry',
        component: AutomatedAccountEntryComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'cs-search',
        component: CsSearchComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'obligations-search',
        component: ObligationsSearchComponent
      },
      {
        path: 'price-increase-upload',
        component: PriceIncreaseUploadComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'aae',
        component: AaeSearchComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'aae/quote/:quoteId/div/:division',
        component: AutomatedAccountEntryComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'dashboard',
        component: DashboardComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'account/:companyId/:account/:site',
        component: AccountComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'account/:companyId/:account/:site/detail',
        component: AccountDetailComponent,
        canActivate: [AuthGuard]
      },
      { path: 'container-detail/:company/:account/:site/:containerId',
         component: ContainerDetailComponent },
      {
        path: '**',
        redirectTo: '/dashboard',
        pathMatch: 'full'
      }

    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes,
    { enableTracing: false })],
  exports: [RouterModule],
  providers: [AuthGuard]
})
export class AppRoutingModule { }
