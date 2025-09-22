package com.repsrv.csweb.core.model.account.openmarket;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class OpenMarketDelRequest {
	
	private String projectId;
	private String projectName;
	private String changeRequest;
	private String fileName;

}
