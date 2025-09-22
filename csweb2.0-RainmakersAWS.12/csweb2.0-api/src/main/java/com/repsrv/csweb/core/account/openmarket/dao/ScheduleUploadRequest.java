package com.repsrv.csweb.core.account.openmarket.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.repsrv.csweb.core.model.json.BaseRequest;
import com.repsrv.csweb.core.model.json.CSWebAction;

public class ScheduleUploadRequest extends BaseRequest{
    
    public ScheduleUploadRequest(String requestedUser, String projectId, String scheduleDate, String scheduleTime) {
        super(requestedUser, CSWebAction.UPLOAD_TO_INFOPRO);
        this.projectId = projectId;
        this.scheduleDate = scheduleDate;
        this.scheduleTime = scheduleTime;
    }

    @JsonProperty("projectId")
    private String projectId;
    @JsonProperty("scheduleDate")
    private String scheduleDate;
    @JsonProperty("scheduleTime")
    private String scheduleTime;

    @JsonIgnore
    @Override
    public Object getRequestedObject() {
    return "";
    }

}

