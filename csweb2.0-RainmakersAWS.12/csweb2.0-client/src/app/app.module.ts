import { ContainerDetailComponent } from './modules/site/container/container-detail/container-detail.component';
import { ObligationInvoiceDetailComponent } from './modules/account/obligations/obligation-invoice-detail/obligation-invoice-detail.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgMaterialMultilevelMenuModule } from 'ng-material-multilevel-menu';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { MatGridListModule } from '@angular/material/grid-list';
import { MaterialFileInputModule } from 'ngx-material-file-input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatStepperModule } from '@angular/material/stepper';
import { TextMaskModule } from 'angular2-text-mask';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatAutocompleteModule} from '@angular/material/autocomplete';
import { MatTabsModule } from '@angular/material/tabs';

import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { APP_BASE_HREF, PlatformLocation } from "@angular/common";
import {MatExpansionModule} from '@angular/material/expansion';
import {MatDialogRef} from '@angular/material/dialog';

import {
  MatToolbarModule,
  MatMenuModule,
  MatIconModule,
  MatButtonModule,
  MatTableModule,
  MatDividerModule,
  MatProgressSpinnerModule,
  MatInputModule,
  MatCardModule,
  MatSlideToggleModule,
  MatSidenavModule,
  MatListModule,
  MatSelectModule,
  MatOptionModule,
  MatChipsModule,
  MatSortModule,
  MatRadioModule,
  MatDialogModule,
  MatFormFieldModule,
  MAT_DATE_FORMATS

} from '@angular/material';
import {MatButtonToggleModule} from '@angular/material/button-toggle';

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MatSelectFilterModule } from 'mat-select-filter';

import { MatCheckboxModule } from '@angular/material/checkbox';
import { AppRoutingModule } from './core/app-routing.module';
import { AppComponent } from './core/app.component';
import { LoginComponent } from './core/authentication/login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { DashboardComponent } from './modules/dashboard/dashboard.component';
import { HttpRequestInterceptor } from './core/interceptors/HttpRequestInterceptor';
import { AuthenticatedlayoutComponent } from './core/layouts/authenticatedlayout/authenticatedlayout.component';
import { AnonymouslayoutComponent } from './core/layouts/anonymouslayout/anonymouslayout.component';
import { SideMenuComponent } from './core/layouts/menu/side-menu/side-menu.component';
import { MassUploadsComponent } from './modules/mass-uploads/mass-uploads.component';
import { SubmitMassUploadComponent } from './modules/mass-uploads/submit-mass-upload/submit-mass-upload.component';
import { MassUploadTemplatesComponent } from './modules/mass-uploads/mass-upload-templates/mass-upload-templates.component';
import { MassUploadHistoryComponent } from './modules/mass-uploads/mass-upload-history/mass-upload-history.component';
import { AaeTemplatesComponent } from './modules/aae-templates/aae-templates.component';
import { AccountOnboardingComponent } from './modules/account-onboarding/account-onboarding.component';
import { AuditComponent } from './modules/audit/audit.component';
import { PriceIncreaseUploadComponent } from './modules/price-increase-upload/price-increase-upload.component';
import { CsSearchComponent } from './modules/cs-search/cs-search.component';
import { FormBuilderTypeSafe } from 'node_modules/angular-typesafe-reactive-forms-helper';
import { AccountComponent} from './modules/account/account.component';
import { AccountDetailComponent } from './modules/account/account-detail/account-detail.component';
import { ObligationsComponent } from './modules/account/obligations/obligations.component';
import { PaymentsComponent } from './modules/account/payments/payments.component';
import { ReconciliationsComponent } from './modules/account/reconciliations/reconciliations.component';
import { AddServiceRecordingComponent } from './modules/account/add-service-recording/add-service-recordings';
import { ChargesComponent } from './modules/account/charges/charges.component';
import { SiteDetailComponent } from './modules/site/site-detail/site-detail.component';
import { SiteRatesComponent } from './modules/site/site-rates/site-rates.component';
import { SiteHaulersComponent } from './modules/site/site-haulers/site-haulers.component';
import { AccountEditComponent } from './modules/account/account-edit/account-edit.component';
import { ContactEditComponent } from './modules/account/contact-edit/contact-edit.component';
import { LoaderBtnDirective } from './common/directives/loader-btn.directive';
import { SiteContactEditComponent } from './modules/site/site-contact-edit/site-contact-edit/site-contact-edit.component';
import { SiteEditComponent } from './modules/site/site-edit/site-edit/site-edit.component';
import { AssetpipePipe } from './common/pipes/assetpipe.pipe';
import { As400DatePipePipe} from './common/pipes/as400-date-pipe.pipe'
import { SingleDigitFormat } from './common/pipes/datePipe/single-digit-format';
import { ViewAllServiceRecordingsComponent } from './modules/account/view-all-service-recordings/view-all-service-recordings.component';
import { MaintainServiceRecordingsComponent } from './modules/account/maintain-service-recordings/maintain-service-recordings.component';
import { ObligationsSearchComponent } from './modules/obligations-search/obligations-search.component';
import { AccountImportComponent } from './modules/account/import/aai/account-import.component';
import { AccountImportHistoryComponent } from './modules/account/import/aai/aai-history/account-import-history.component';
import { DialogAccountImportPreview } from './modules/account/import/aai/account-import.component';
import { DialogAccountImportPreview3 } from './modules/account/imports3/account-imports3/account-imports3.component';
import { PreviewSheetsComponent } from './modules/account/import/aai/preview-sheets/preview-sheets.component';
import { PreviewSheetsComponent3 } from './modules/account/imports3/account-imports3/preview-sheets/preview-sheets.component';
import { PreviewLiveComponent } from './modules/account/import/aai/preview-live/preview-live.component';
import { PreviewLiveComponent3 } from './modules/account/imports3/account-imports3/preview-live/preview-live.component';
import { ResiContractComponent } from './modules/account/import/aai/preview-live/resi-contract/resi-contract.component';
import { SubmitConfirmComponent } from './modules/account/import/aai/submit-confirm/submit-confirm.component';
import { SubmitConfirmComponent3 } from './modules/account/imports3/account-imports3/submit-confirm/submit-confirm.component';
import { ServicenumberfieldComponent } from './core/layouts/servicenumberfield/servicenumberfield.component';
import { SiteHcmtComponent } from './modules/site/site-hcmt/site-hcmt.component';
import { DateFormatPipe } from './date-format.pipe';
import { PhonePipe } from './common/pipes/phone.pipe';
import { AutomatedAccountEntryComponent } from './modules/aae/entry/aae-entry.component';
import {AaeSearchComponent} from './modules/aae/aae-search.component';
import { SalesTransactionsEditComponent } from './modules/aae/entry/sales-transactions-edit/sales-transactions-edit/sales-transactions-edit.component';
import { AAEAccountEditComponent } from './modules/aae/entry/account-edit/account-edit/aae-account-edit.component';
import { Container } from '@angular/compiler/src/i18n/i18n_ast';
import { FinalValidationComponent } from './modules/aae/entry/final-validation/final-validation/final-validation.component';
import { RateEditComponent } from './modules/aae/entry/rate-edit/rate-edit/rate-edit.component';
import { ContainerEditComponent } from './modules/aae/entry/container-edit/container-edit/container-edit.component';
import { RouteEditComponent } from './modules/aae/entry/route-edit/route-edit/route-edit.component';
import { AAESiteEditComponent } from './modules/aae/entry/site-edit/site-edit/aae-site-edit.component';
import { AAEContactEditComponent } from './modules/aae/entry/contact-edit/contact-edit/aae-contact-edit.component';
import { AAEAccountContactEditComponent } from './modules/aae/entry/account-contact-edit/aae-account-contact-edit/aae-account-contact-edit.component';
import { GhostTableComponent } from './modules/ghost-table/ghost-table.component';
import { RateCreateComponent } from './modules/aae/entry/rate-create/rate-create/rate-create.component';
import { QuoteValidateComponent } from './modules/aae/entry/quote-validate/quote-validate/quote-validate.component';
import {  MatBottomSheetModule } from '@angular/material/bottom-sheet';
import { AutoCompleteDropdownComponent } from './modules/auto-complete-dropdown/auto-complete-dropdown.component';
import { RateDeleteComponent } from './modules/aae/entry/rate-delete/rate-delete/rate-delete.component';
import { ContractInfoComponent } from './modules/aae/entry/contract-info/contract-info/contract-info.component';
import { FormatPhonePipe } from './common/pipes/phonePipe/format-phone.pipe';
import { FormatEnteredDatePipe } from './common/pipes/datePipe/format-entered-date.pipe';
import { FormatNumDatePipe } from './common/pipes/datePipe/format-num-date.pipe';
import { ScrollingModule } from '@angular/cdk/scrolling';
import { ContainerDeleteComponent } from './modules/aae/entry/container-delete/container-delete.component';
import { FutureServiceRequestEditComponent } from './modules/aae/entry/fsr-edit/fsr-edit/fsr-edit.component';
import { FutureServiceRequestDeleteComponent } from './modules/aae/entry/fsr-delete/fsr-delete/fsr-delete.component';
import { WindowbuttonsDirective } from './windowbuttons.directive';
import { ErrorReportComponent } from './modules/aae/entry/error-report/error-report/error-report.component';
import { AddNotesComponent } from './modules/aae/add-notes/add-notes.component';
import { ViewNotesComponent } from './modules/aae/view-notes/view-notes.component';
import { OpenmarketComponent } from './modules/account/import/aai/aai-history/tables/aom/openmarket.component';
import { ReviewmodalComponent } from './modules/account/import/aai/aai-history/tables/aom/reviewmodal/reviewmodal.component';
import { ChangeuploadComponent } from './modules/account/import/aai/aai-history/tables/aom/changeupload/changeupload.component';
import { ContainerServiceHistoryComponent } from './modules/site/container-service-history/container-service-history/container-service-history.component';
import { IndStatSrvHistoryComponent } from './modules/site/industrial-statistics-service-history/ind-stat-srv-history/ind-stat-srv-history.component';
import { AccountImports3Component } from './modules/account/imports3/account-imports3/account-imports3.component';
import { Aob3ProjectsComponent } from './modules/account/imports3/aob3-history/aob3-projects/aob3-projects.component';
import { Aob3ProjectHistoryComponent } from './modules/account/imports3/aob3-project-details/aob3-project-history/aob3-project-history.component';


export const MY_FORMATS = {
  parse: {
    dateInput: ['YYYY-MM-DD'],
  },
  display: {
    dateInput: 'YYYY-MM-DD',
    monthYearLabel: 'YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'YYYY',
  },
};

export function getBaseHref(platformLocation: PlatformLocation): string {
    return platformLocation.getBaseHrefFromDOM();
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    AuthenticatedlayoutComponent,
    AnonymouslayoutComponent,
    SideMenuComponent,
    MassUploadsComponent,
    SubmitMassUploadComponent,
    MassUploadTemplatesComponent,
    MassUploadHistoryComponent,
    AaeTemplatesComponent,
    AccountOnboardingComponent,
    AuditComponent,
    PriceIncreaseUploadComponent,
    CsSearchComponent,
    AddServiceRecordingComponent,
    AccountComponent,
    AccountDetailComponent,
    ObligationsComponent,
    ObligationInvoiceDetailComponent,
    PaymentsComponent,
    ReconciliationsComponent,
    ChargesComponent,
    SiteDetailComponent,
    ContainerDetailComponent,
    SiteRatesComponent,
    SiteHaulersComponent,
    SiteHcmtComponent,
    AccountEditComponent,
    LoaderBtnDirective,
    ContactEditComponent,
    SiteContactEditComponent,
    SiteEditComponent,
    AssetpipePipe,
    As400DatePipePipe,
    SingleDigitFormat,
    ViewAllServiceRecordingsComponent,
    MaintainServiceRecordingsComponent,
    ObligationsSearchComponent,
    AccountImportComponent,
    AccountImportHistoryComponent,
    DialogAccountImportPreview,
    DialogAccountImportPreview3,
    PreviewSheetsComponent,
    PreviewSheetsComponent3,
    PreviewLiveComponent,
    PreviewLiveComponent3,
    ResiContractComponent,
    SubmitConfirmComponent,
    SubmitConfirmComponent3,
    ServicenumberfieldComponent,
    DateFormatPipe,
    PhonePipe,
    AutomatedAccountEntryComponent,
    AaeSearchComponent,
    SalesTransactionsEditComponent,
    AAEAccountEditComponent,
    ContainerEditComponent,
    FinalValidationComponent,
    RateEditComponent,
    RouteEditComponent,
    AAESiteEditComponent,
    AAEContactEditComponent,
    AAEAccountContactEditComponent,
    GhostTableComponent,
    RateCreateComponent,
    QuoteValidateComponent,
    AutoCompleteDropdownComponent,
    RateDeleteComponent,
    ContractInfoComponent,
    FormatPhonePipe,
    FormatEnteredDatePipe,
    FormatNumDatePipe,
    ContainerDeleteComponent,
    FutureServiceRequestEditComponent,
    FutureServiceRequestDeleteComponent,
    WindowbuttonsDirective,
    ErrorReportComponent,
    AddNotesComponent,
    ViewNotesComponent,
    OpenmarketComponent,
    ReviewmodalComponent,
    ChangeuploadComponent,
    ContainerServiceHistoryComponent,
    IndStatSrvHistoryComponent,
    AccountImports3Component,
    Aob3ProjectsComponent,
    Aob3ProjectHistoryComponent,
  ],
  imports: [
    MatSelectFilterModule,
    BrowserModule,
    AppRoutingModule,
    MatCheckboxModule,
    MatTabsModule,
    FlexLayoutModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatInputModule,
    MatCardModule,
    MatMenuModule,
    MatIconModule,
    MatButtonModule,
    MatTableModule,
    MatDividerModule,
    MatSlideToggleModule,
    MatSelectModule,
    MatOptionModule,
    MatSortModule,
    MatProgressSpinnerModule,
    HttpClientModule,
    MatSidenavModule,
    MatListModule,
    NgMaterialMultilevelMenuModule,
    FontAwesomeModule,
    MatGridListModule,
    MatChipsModule,
    MaterialFileInputModule,
    MatPaginatorModule,
    MatRadioModule,
    MatDialogModule,
    MatStepperModule,
    TextMaskModule,
    MatSnackBarModule,
    MatAutocompleteModule,
    MatProgressBarModule,
    MatTooltipModule,
    MatButtonToggleModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatExpansionModule,
    MatFormFieldModule,
    MatBottomSheetModule,
    ScrollingModule
  ],
  exports: [LoaderBtnDirective],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: HttpRequestInterceptor, multi: true },
    //{provide:MatDialogRef , useValue:{} },
    {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS},
    {
            provide: APP_BASE_HREF,
            useFactory: getBaseHref,
            deps: [PlatformLocation]
        },
    FormBuilderTypeSafe,
    MatDatepickerModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
