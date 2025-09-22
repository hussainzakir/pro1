package com.repsrv.csweb.core.model.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.repsrv.csweb.core.model.codes.StandardizedCode;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DetailsWrapper {
		@JsonProperty("Details")
		public List<StandardizedCode> codes;
		public DetailsWrapper() {}
}
