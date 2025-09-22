package com.repsrv.csweb.core.model.account.aob3;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Aob3ProjectDetails {

    private String projectName;

    private String startDate;

    private String projectStatus;

    private String completionDate;

    private int noOfAccounts;

    private int noOfBillTos;

    private int noOfSites;

    private int noOfContainers;

    private int noOfRates;

    private int noOfRoutes;

    private String currentStep;

    private String currentStepStatus;

    private String error;

    private String lastAttempt;

    private String lastValidated;

    private String nextStep;

    @SerializedName("activityhistory")
    private List<ActivityHistory> activityHistory;
    
}
