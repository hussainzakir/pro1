package com.repsrv.csweb.core.account.onboarding.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.repsrv.csweb.core.model.json.BaseRequest;
import com.repsrv.csweb.core.model.json.CSWebAction;

public class AobRequestDriverReq extends BaseRequest{

    public AobRequestDriverReq(String requestedUser, String projectId) {
        super(requestedUser, CSWebAction.GET_ALL);
        this.projectId = projectId;
    }

    @JsonProperty("projectId")
    private String projectId;

    @JsonIgnore
    @Override
    public Object getRequestedObject() {
    return "";
    }
}
