package com.repsrv.csweb.core.authorization.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.util.AuthResponseDeserializer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = AuthResponseDeserializer.class)
public class AuthResponse extends StoredProcCallResult {
    
    private String username;
    private List<String> pointers;
    private SuperUserPermissions super_perms;
    private List<DivisionsInfo> divisions;
	
	@Override
    public String toString() {
        return "AuthResponse{" +
                "username='" + username + '\'' +
                ", pointers=" + pointers +
                ", super_perms=" + super_perms +
                ", divisions=" + divisions +
                ", errorCode='" + getErrorCode() + '\'' +
                ", errorMessage='" + getErrorMessage() + '\'' +
                '}';
    }
}
