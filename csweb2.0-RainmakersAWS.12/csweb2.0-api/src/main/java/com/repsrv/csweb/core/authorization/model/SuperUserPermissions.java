package com.repsrv.csweb.core.authorization.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuperUserPermissions {
	private String consolidatedAAE;
	private String divisionalAAE;
	private String homeDivision;
	private String superUser;
	
	@Override
    public String toString() {
        return "SuperUserPermissions{" +
                "divisionalAAE='" + divisionalAAE + '\'' +
                ", consolidatedAAE='" + consolidatedAAE + '\'' +
                ", homeDivision='" + homeDivision + '\'' +
                ", superUser='" + superUser + '\'' +
                '}';
    }
}
