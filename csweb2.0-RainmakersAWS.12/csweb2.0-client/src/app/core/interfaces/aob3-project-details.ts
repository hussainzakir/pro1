export interface ActivityHistory {
  step: number;
  activity: string;
  status: string;
  startTime: string;
  endTime: string;
}

export interface Aob3ProjectDetails {
  projectName: string;
  startDate: string;
  projectStatus: string;
  completionDate: string;
  noOfAccounts: number;
  noOfBillTos: number;
  noOfSites: number;
  noOfContainers: number;
  noOfRates: number;
  noOfRoutes: number;
  currentStep: string;
  currentStepStatus: string;
  error: string;
  lastAttempt: string;
  lastValidated: string;
  nextStep: string;
  activityhistory: ActivityHistory[];
}