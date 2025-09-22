package com.repsrv.csweb.core.model.error;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {

	private String errorMsg;
	private String errorCode;
	
}
