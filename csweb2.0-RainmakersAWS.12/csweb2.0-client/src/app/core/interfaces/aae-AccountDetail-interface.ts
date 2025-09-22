export interface AccountDetail {
  accountStagingId?:              string;
  company?:                       string;
  account?:                       string;
  attention?:                     string;
  accountAlias?:                  string;
  customerName?:                  string;
  streetType?:                    string;
  streetDirection?:               string;
  addressNumber?:                 string;
  addressName?:                   string;
  addressLine1?:                  string;
  addressLine2?:                  string;
  addressLine3?:                  string;
  telephone?:                     string;
  phoneExtension?:                number;
  fax?:                           string;
  city?:                          string;
  state?:                         string;
  postalCode?:                    string;
  customerCategory?:              string;
  parcelNumber?:                  string;
  accountClassTable?:             string;
  cashTolerance?:                 string;
  chainCode?:                     string;
  lastUpdated?:                   number;
  invoiceDestination?:            string;
  invoicePageBreak?:              string;
  remitCode?:                     string;
  printMethod?:                   string;
  printMethodDescription?:        string;
  invoiceSiteTotals?:             string;
  invoiceSiteTaxes?:              string;
  invoiceContainerTotals?:        string;
  invoiceContainerTaxes?:         string;
  acquisitionCode?:               string;
  languageCode?:                  string;
  languageDescription?:           string;
  siEligible?:                    string;
  siStatus?:                      string;
  siStartDate?:                   number;
  siStatusDescription?:           string;
  invoiceGroupCode?:              string;
  creditPolicy?:                  string;
  riskCodeDescription?:           null;
  originalStartDate?:             string;
  lastInvoiceDate?:               string;
  dunningDate?:                   string;
  dunningStage?:                  string;
  creditAnalyst?:                 string;
  chargeAdministrativeFee?:       string;
  administrativeFeeExc?:          string;
  chargeServiceIntFee?:           string;
  serviceIntFeeExc?:              string;
  chargeLateFee?:                 string;
  lateFeeExc?:                    string;
  lateFeePolicy?:                 string;
  chargeFuelFee?:                 string;
  fuelFeeExc?:                    string;
  nextReviewDate?:                string;
  chargeEnvironmentalFee?:        string;
  environmentalFeeExc?:           string;
  duplicateCopy?:                 string;
  ediRequired?:                   string;
  brokerCode?:                    string;
  accountGroupId?:                string;
  parentD?:                       string;
  onlineAccountFlag?:             string;
  accountContactName?:            string;
  accountContactTitle?:           string;
  accountContactEmail?:           string;
  accountContactAreaCode1?:       string;
  accountContactTelephone1?:      string;
  accountContactTelephoneExtn1?:  string;
  accountContactType1?:           string;
  accountContactAreaCode2?:       string;
  accountContactTelephone2?:      string;
  accountContactTelephoneExtn2?:  string;
  accountContactType2?:           string;
  erfFrfFlag?:                    string;
  taxCode?:                       string;
  distributionCode?:              string;
  onlinePaymentProfile?:          string;
  accountClassDesc?:              string;
  acquisitionCodeDesc?:           string;
  formatType?:                    string;
  sites?:                         Site[];
}


export interface Site {
  siteStagingId?:                 string;
  companyNumber?:                 string;
  site?:                          string;
  siteName?:                      string;
  city?:                          string;
  addressName?:                   string;
  addressNumber?:                 string;
  stateProvince?:                 string;
  postalCode?:                    string;
  streetDirection?:               string;
  addressNumberName?:             string;
  siteAddress2?:                  string;
  streetNumber?:                  string;
  streetType?:                    string;
  addressSuite?:                  string;
  serviceContact?:                string;
  contactTitle?:                  string;
  phoneNumber?:                   string;
  phoneExtension?:                number;
  contractPriceIndex?:            string;
  contractNumber?:                string;
  term?:                          number;
  termDescription?:               string;
  effectiveDate?:                 string;
  expiryDate?:                    string;
  reviewDate?:                    string;
  closeDate?:                     string;
  cpiIndicator?:                  string;
  contractStatus?:                string;
  contractStatusDescription?:     string;
  purchaseOrder?:                 string;
  authorizedBy?:                  string;
  authorizedByTitle?:             string;
  sicCode?:                       string;
  sicCodeDescription?:            string;
  salesRep?:                      string;
  salesRepName?:                  string;
  sharedContainers?:              string;
  territoryCode?:                 string;
  territoryCodeDescription?:      null;
  taxCode?:                       string;
  taxCodeDescription?:            null;
  taxExempt?:                     string;
  lastUpdatedDate?:               string;
  originalStartDate?:             number;
  siteContactName?:               string;
  siteContactTitle?:              string;
  siteContactEmail?:              string;
  siteContactAreaCode1?:          string;
  siteContactTelephone1?:         string;
  siteContactTelephoneExtn1?:     string;
  siteContactType1?:              string;
  siteContactAreaCode2?:          string;
  siteContactTelephone2?:         string;
  siteContactTelephoneExtn2?:     string;
  siteContactType2?:              string;
  siteContactFaxAreaCode?:        string;
  siteContactFaxNumber?:          string;
  faxNumber?:                     string;
  naics?:                         string;
  preDirectional?:                string;
  containers?:                    Container[];
}

export interface Container {
  containerStagingId?:            string;
  companyNumber?:                 string;
  containerId?:                   number;
  accountType?:                   string;
  accountTypeDescription?:        string;
  containerType?:                 string;
  containerSize?:                 string;
  compactor?:                     string;
  quantityOrder?:                 number;
  containerQtyOnSite?:            string;
  closeDate?:                     string;
  containerStatus?:               string;
  specialHandling?:               string;
  specialHandlingDescription?:    string;
  customerOwned?:                 string;
  poRequired?:                    string;
  onCall?:                        string;
  billingFrequency?:              number;
  revenueDistribution?:           string;
  sharedContainerId?:             string;
  gridNumber?:                    string;
  contractGroup?:                 number;
  contractNumber?:                string;
  originalStartDate?:             string;
  startDate?:                     string;
  removalDate?:                   string;
  estimatedLifts?:                string;
  totalLifts?:                    string;
  mondayRequired?:                string;
  tuesdayRequired?:               string;
  wednesdayRequired?:             string;
  thursdayRequired?:              string;
  fridayRequired?:                string;
  saturdayRequired?:              string;
  sundayRequired?:                string;
  mondayLiftDay?:                 string;
  tuesdayLiftDay?:                string;
  wednesdayLiftDay?:              string;
  thursdayLiftDay?:               string;
  fridayLiftDay?:                 string;
  saturdayLiftDay?:               string;
  sundayLiftDay?:                 string;
  periodLength?:                  string;
  peoridUnit?:                    string;
  disposalCode?:                  string;
  unitOfMeasure?:                 string;
  weightLimit?:                   string;
  recurMnthsAdvBill?:             string;
  disposalRateRestriction?:       string;
  cityAccountNumber?:             string;
  receiptRequired?:               string;
  billedToDate?:                  string;
  remoteMonitorFlag?:             string;
  rateRestrOperDate?:             number;
  lastServiceDate?:               number;
  containerIdCode?:               string;
  disposalPriceCode?:             string;
  stopCode?:                      string;
  rateType?:                      string;
  sdtransactionCode?:             string;
  sdReasonCode?:                  string;
  sdTransReasonDescription?:      string;
  sdServicingRep?:                string;
  sdServicingRepName?:            string;
  sdSigningRep?:                  string;
  sdSigningRepName?:              string;
  sdCompetitorCode?:              string;
  sdCsaContractNumber?:           string;
  sdLeadSource?:                  string;
  sdLeadSourceDescription?:       string;
  routeCreateDelUR?:              string;
	routeSchPermService?:           string;
  routeUrEffectiveDate?:          string;
	routeUrPlanDate?:               string;
	routeUrSchEffectiveDate?:       string;
	routeUrSchPlanDate?:            string;
	routePOnumber?:                 string;
	routeNotes1?:                   string;
	routeNotes2?:                   string;
	routeNotes3?:                   string;
	routeNotes4?:                   string;
	routeNotes5?:                   string;
  routeNotes6?:                   string;
	routeNotes7?:                   string;
	routeWeekDelayService?:         string;
  routeNotes?:                    string;
  routeQuantity?:                 string;
  routeRequestedDelivery?:        string;
  routeServiceCode?:              string;
  districtCode?:                  string;
  contractDescription?:           string;
  contractName?:                  string;
  fsrEffectiveDate?:              string;
  fsrQtyOnOrder?:                 string;
  fsrTotalLifts?:                 string;
  fsrTimeFrame?:                  string;
  fsrTimeFrameRef?:               string;
  fsrOnCall?:                     string;
  fsrMinLifts?:                   string;
  idCode?:                        string;
  rates?:                         Rate[];
}

export interface Rate {
  rateStagingId?:                 string;
  company?:                       string;
  customerAccount?:               string;
  site?:                          string;
  containerGroup?:                number;
  sharedContainerId?:             string;
  chargeCode?:                    string;
  chargeRate?:                    string;
  chargeType?:                    string;
  chargeMethod?:                  string;
  taxApplicationCode?:            string;
  billingAdjustFlag?:             string;
  effectiveDate?:                 string;
  closeDate?:                     string;
  lastUpdateUser?:                string;
  lastUpdateDate?:                string;
  chargeCodeDescription?:         string;
  wasteMaterialType?:             string;
  unitOfMeasure?:                 string;
}

export interface finalization {
  projectId?:               string;
  validateOnly?:            string;
}

export enum ChargeMethod {
  F = "F",
  Q = "Q",
  S = "S",
}

export enum ChargeType {
  F = "F",
  R = "R",
  S = "S",
}

export interface QuoteMetadata{
  projectId?: string;
  quoteNumber?: string;
  statusCode?: string;
  assignedTo?: string;
  quoteType?: string;
  reason?: string;
  formatType?: string;
  allowFinalize?: string;
  allowUpdate?: string;
  lastValidatedTimestamp?: string;
}

