namespace CswebModel{
  export interface UserAuthority{
    menuOption: string;
      authenticated: boolean;
  }

  export interface User{
      name: string;
      authorities: UserAuthority[];
  }

  export interface TableCode{
    tableElement: string;
    description: string;

  }

  export interface Employee{
    employeeNumber: string;
    firstName: string
    lastName: string
    employeeType: string
  }

  export interface ServiceRequest{
   company: string;
   account: string;
  site: string;
  container: number;
  serviceCode: string;
  reasonCode: string;
  requestedHaulDate: string;
  numberOfLifts: number;
  poNumber: string;
  orderSource: string;
  reportedByName: string;
  notes: string;
  serviceRequestNumber: string;
  timeBefore: string;
  timeAfter: string;
  route: string;
  routeDate: string;
  origNeedDate: string;
  createDate: string;
  updateDate: string;
  updateUser: string;
  supplementalFlag: string;
  commitmentDate: string;
  }

  export interface ServiceRecording{
    status: string;
    companyNumber: string;
    type: string;
    accountNumber: string;
    siteNumber: string;
    originatedBy: string;
    reportedBy: string;
    routeNumber: string;
    truckNumber: string;
    employeeNumber: string;
    notes: string;
    priority: string;
    escalationLevel: string;
    assignedTo: string;

  }
}
