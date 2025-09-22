package com.repsrv.csweb.core.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BuildIdService {

	@Value("${jenkins-build:build not from Jenkins}")
	private String buildId;
	
	public String getBuildId() {
		return this.buildId;
	}
}
