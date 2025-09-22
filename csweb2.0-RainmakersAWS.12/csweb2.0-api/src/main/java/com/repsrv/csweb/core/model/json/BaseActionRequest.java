package com.repsrv.csweb.core.model.json;

import java.io.Serializable;

import com.repsrv.csweb.core.support.exception.StoredProcedureException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseActionRequest implements Serializable{
    private static final long serialVersionUID = 938172001061919077L;

    private static final Logger logger = LoggerFactory.getLogger(BaseActionRequest.class);

    @JsonProperty("action")
    protected CSWebAction action;

    @JsonProperty("requestedUser")
    protected String requestedUser;

    @JsonIgnore
    private String returnStatus;

    @JsonIgnore
    private String returnErrorMsg;

    public BaseActionRequest() {}

    public BaseActionRequest(String requestedUser, CSWebAction action) {
        Assert.hasText(requestedUser, "RequestedUser is required");
        Assert.notNull(action, "CSWebAction cannot be null");

        this.requestedUser = requestedUser;
        this.action = action;

    }

    /**
     * The object returned will be serialized into a JSON object.
     * Be sure to annotate with property identifier, like: @JsonProperty(value = "haulerInformation")
     * @return
     */
    public abstract Object getRequestObject();


    @JsonIgnore
    public String getJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            logger.error("Failed to generate JSON string for request", e);
        }
        return null;
    }

    public String getRequestedUser() {
        return requestedUser;
    }

    public void setRequestedUser(String requestedUser) {
        this.requestedUser = requestedUser;
    }

    public CSWebAction getAction() {
        return action;
    }

    @JsonIgnore
    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    @JsonIgnore
    public String getReturnErrorMsg() {
        return returnErrorMsg;
    }

    public void setReturnErrorMsg(String returnErrorMsg) {
        this.returnErrorMsg = returnErrorMsg;
    }

    public void validateResponse()  {
        if(StringUtils.isNotBlank(this.getReturnStatus()) &&
                !this.getReturnStatus().trim().equals("00000"))
            throw new StoredProcedureException(this.returnErrorMsg);
    }

}

