export interface ServiceRecording {
  account : string;
company : string;
status : string;
assetGroup : string;
assignedToUser : string;
containerDescription : string;
containerType : string;
extraLiftFlag : string;
lastUpdateDate : string;
lastUpdateTime : string;
lastUpdateUser : string;
numberOfLifts : number
originalRecordingType : string;
originalUser : string;
purchaseOrderNumber : string;
recordClosedFlag : string;
recordingPriority : string;
reportedByName : string;
routeNumber : string;
scheduleCompletionDate : string;
serviceCode : string;
serviceDescription : string;
serviceRecordingCompositeKey : string;
serviceRecordDate : string;
ServiceRecordNote : string;
serviceRecordingNumber : number
serviceRequestCompositeKey : string;
serviceRequestNumber : number
serviceRequestScheduled : string;
serviceResolutionNote : string;
siteCompositeKey : string;
siteId : string;
timeRequestedAfter : number
timeRequestedBefore : number
transactionCode : string;
transactionDescription : string;
unscheduledRequestNote : string;
}


export interface TemplateItem {
  name: string;
  updatedDate: string;
}
