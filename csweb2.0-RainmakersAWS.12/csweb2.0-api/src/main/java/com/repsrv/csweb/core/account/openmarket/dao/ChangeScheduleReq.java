package com.repsrv.csweb.core.account.openmarket.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangeScheduleReq {
 
 public ChangeScheduleReq(String requestedUser, String projectId, String scheduleDate, String scheduleTime, String action) {
        this.requestedUser = requestedUser;
        this.projectId = projectId;
        this.scheduleDate = scheduleDate;
        this.scheduleTime = scheduleTime;
        this.action = action;
    }

    private String requestedUser;
    private String projectId;
    private String scheduleDate;
    private String scheduleTime;
    private String action;
    private String returnStatus;
    private String returnErrorMsg;

}
