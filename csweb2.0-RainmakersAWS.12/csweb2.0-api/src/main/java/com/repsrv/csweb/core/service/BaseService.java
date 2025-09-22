package com.repsrv.csweb.core.service;

import org.springframework.beans.factory.annotation.Value;

public class BaseService {

	@Value("${system.id}")
	private String system;
	
	protected String getSystem() {
		return this.system;
	}
}
