package com.repsrv.csweb.core.model.massupload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class MuHistory implements Comparable<MuHistory>{

	private String submitted;
	private String lastUpdated;
	private String filename = "";
	private String status;
	private String tableToUpdate;
	private String logId;
	private int totalRecords;
	private int totalClosed;
	private int totalChanged;
	private int totalAdded;
	private int errorCount;
	private String system;
	private String environment="D";
	private String templateName;
	private String submittedFile;
	private String templateId;
	private String errorFilePath;
	private String submittedUser;
	


	@Override
	public int compareTo(MuHistory arg) {
		return this.getSubmitted().compareTo(arg.getSubmitted());
	}
	
	
}
