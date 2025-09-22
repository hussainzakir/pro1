package com.repsrv.csweb.core.authorization.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DivisionsInfo {

	@JsonProperty("ug")
	private String userGroup;
	
	@JsonProperty("div")
	private String division;

	@Override
    public String toString() {
        return "DivisionsInfo{" +
                "ug='" + userGroup + '\'' +
                ", division='" + division + '\'' +
                '}';
    }
}
