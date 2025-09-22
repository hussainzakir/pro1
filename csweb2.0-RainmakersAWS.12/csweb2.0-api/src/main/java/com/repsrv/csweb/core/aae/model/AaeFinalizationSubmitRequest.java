package com.repsrv.csweb.core.aae.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.repsrv.csweb.core.model.aae.FinalizationParams;
import com.repsrv.csweb.core.model.json.BaseRequest;
import com.repsrv.csweb.core.model.json.CSWebAction;

public class AaeFinalizationSubmitRequest  extends BaseRequest {

private FinalizationParams finalizationParams;

    public AaeFinalizationSubmitRequest(CSWebAction action, String requestedUser,  FinalizationParams finalizationParams) {
        super(requestedUser, action);
        this.finalizationParams = finalizationParams;
    }
    @Override
    @JsonProperty(value = "data")
    public Object getRequestedObject() {
        return this.finalizationParams;
    }
}
