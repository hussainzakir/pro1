package com.repsrv.csweb.core.model;

import java.util.List;

import com.repsrv.csweb.core.authorization.model.DivisionsInfo;
import com.repsrv.csweb.core.model.authorization.UserDivision;

public class User {

	private String username;
	
	private List<DivisionsInfo> userDivisions;

	public User(String name, List<DivisionsInfo> divisions) {
		this.username = name;
		this.userDivisions = divisions;
	}

	public String getUsername() {
		return username == null ? null : this.username.toUpperCase();
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<DivisionsInfo> getUserDivisions() {
		return userDivisions;
	}

	public void setUserDivisions(List<DivisionsInfo> userDivisions) {
		this.userDivisions = userDivisions;
	}
	
	
}
