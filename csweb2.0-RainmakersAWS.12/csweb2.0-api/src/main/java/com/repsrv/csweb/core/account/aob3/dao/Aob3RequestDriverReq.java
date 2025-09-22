package com.repsrv.csweb.core.account.aob3.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.repsrv.csweb.core.model.json.BaseRequest;
import com.repsrv.csweb.core.model.json.CSWebAction;

public class Aob3RequestDriverReq extends BaseRequest{

    public Aob3RequestDriverReq(String requestedUser, String projectId, String uploadType) {
        super(requestedUser, CSWebAction.GET_ALL);
        this.projectId = projectId;
        this.uploadType = uploadType;
    }

    @JsonProperty("projectId")
    private String projectId;

    @JsonProperty("uploadType")
    private String uploadType;

    @JsonIgnore
    @Override
    public Object getRequestedObject() {
    return "";
    }
}