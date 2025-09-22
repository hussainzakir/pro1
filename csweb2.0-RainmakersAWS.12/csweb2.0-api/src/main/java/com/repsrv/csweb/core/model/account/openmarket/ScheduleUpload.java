package com.repsrv.csweb.core.model.account.openmarket;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class ScheduleUpload {
    
    private String projectId;
	private String scheduleDate;
	private String scheduleTime;
    private String uploadType;
}


