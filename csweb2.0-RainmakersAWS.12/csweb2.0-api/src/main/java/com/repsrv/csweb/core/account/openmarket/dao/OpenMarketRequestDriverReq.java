package com.repsrv.csweb.core.account.openmarket.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.repsrv.csweb.core.model.json.BaseRequest;
import com.repsrv.csweb.core.model.json.CSWebAction;

public class OpenMarketRequestDriverReq extends BaseRequest {
       
    public OpenMarketRequestDriverReq(String requestedUser, String projectId) {
        super(requestedUser, CSWebAction.UPLOAD_TO_Y);
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
