package com.repsrv.csweb.core.model.account.aob3;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

@Setter
@Getter
@Log

public class Aob3UploadHistoryRequest extends StoredProcCallResult {

    private String requestedUser;
    private String uploadType;
    private String returnJson;

    public Aob3UploadHistoryRequest(String requestedUser, String uploadType) {
        this.requestedUser = requestedUser;
        this.uploadType = uploadType;
        this.returnJson = "1";
    }

	@JsonIgnore
	public String getJson() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
		    log.info("Failed to generate JSON string for request: "+ e);
		}
		return null;
	}
}
