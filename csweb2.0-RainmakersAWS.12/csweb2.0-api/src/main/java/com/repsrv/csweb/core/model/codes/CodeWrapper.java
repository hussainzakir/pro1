package com.repsrv.csweb.core.model.codes;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CodeWrapper{
	@JsonProperty("Details")
	public List<StandardizedCode> codes;
	public CodeWrapper() {}

}
