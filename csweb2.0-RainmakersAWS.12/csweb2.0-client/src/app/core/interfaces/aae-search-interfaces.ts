import { PageEvent } from '@angular/material/paginator';
export interface AaeSearchRecord {
  projectID: number;
  quoteID: string;
  oracleDivision: number;
  companyNumber: string;
  accountNumber: string;
  accountName: string;
  siteName: string;
  siteAddress: string;
  statusCode: string;
  createDate: string;
  siteStartDate: number;
  salesRep: string;
  assignedTo: string;
}

export interface AaeSearchResult{
  records: AaeSearchRecord[];
  page: number;
  pageSize: number;
  totalRecords: number;

}

export enum StatusCode {
  RVW = "Review",
  PRI = "Primary",
  HLD = "Hold",
  DEL = "Delete"
}

