package com.repsrv.csweb.core.model.account.aob3;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityHistory {

    private int step;

    private String activity;

    private String status;

    private String startTime;

    private String endTime;
    
}
