package com.repsrv.csweb.core.aae.model;

import com.repsrv.csweb.core.model.sp.StoredProcCallResult;

public class GetAccountNumberRequest extends StoredProcCallResult {
   
    private String projectId;
    private String outStatus;
    private String outErrorMsg;
    private String outNewAccount;

    
    public GetAccountNumberRequest(String projectId) {
        this.projectId = projectId;
    }

    public String getOutStatus() {
        return outStatus;
    }

    public void setOutStatus(String outStatus) {
        this.outStatus = outStatus;
    }

    public String getOutErrorMsg() {
        return outErrorMsg;
    }

    public void setOutErrorMsg(String outErrorMsg) {
        this.outErrorMsg = outErrorMsg;
    }

    public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

    public String getOutNewAccount() {
        return outNewAccount;
    }

    public void setOutNewAccount(String outNewAccount) {
        this.outNewAccount = outNewAccount;
    }
}
