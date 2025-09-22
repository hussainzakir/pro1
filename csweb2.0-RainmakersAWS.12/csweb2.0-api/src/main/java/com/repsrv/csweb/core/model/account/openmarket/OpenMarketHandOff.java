package com.repsrv.csweb.core.model.account.openmarket;
import static com.repsrv.csweb.core.util.JsonUtil.gsonString;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OpenMarketHandOff {	

	@JsonProperty("projectId")
	private String projectId;
	@JsonProperty("requestEntity")
	private String requestEntity;
	@JsonProperty("requestSource")
	private String requestSource;
	@JsonProperty("requestData")
	private Object entityData;
	@JsonProperty("requestedUser")
	private String requestedUser;
		
	@Override
	public String toString() {
		return "openMarketHAndOff METADATA" +  "ENTITY: " + gsonString(entityData) + "]";
	}
}
