package com.repsrv.csweb.core.model.account.aob3;
import static com.repsrv.csweb.core.util.JsonUtil.gsonString;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Aob3Handoff {

	public Aob3Handoff( String projectId, String requestedUser, String requestEntity, Object entityData) {
		this.projectId = projectId;
		this.requestEntity = requestEntity;
		this.entityData = entityData;
		this.requestedUser = requestedUser;
	}
	

	@JsonProperty("projectId")
	private String projectId;
	@JsonProperty("requestEntity")
	private String requestEntity;
	@JsonProperty("requestData")
	private Object entityData;
	@JsonProperty("requestedUser")
	private String requestedUser;
		
	@Override
	public String toString() {
		return "aob3HandOff METADATA" +  "ENTITY: " + gsonString(entityData) + "]";
	}
}


