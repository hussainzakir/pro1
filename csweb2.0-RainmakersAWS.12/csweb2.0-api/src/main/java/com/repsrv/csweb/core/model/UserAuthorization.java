package com.repsrv.csweb.core.model;

import org.springframework.security.core.GrantedAuthority;

public class UserAuthorization implements GrantedAuthority{

	private String menuOption;
	private String jobType;
	private String node;
	private String preMenu;
	
	public UserAuthorization() {}

	public UserAuthorization(String menuOption) {
		this.menuOption = menuOption;
	}
	
	public String getMenuOption() {
		return menuOption;
	}
	public void setMenuOption(String menuOption) {
		this.menuOption = menuOption;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public String getPreMenu() {
		return preMenu;
	}
	public void setPreMenu(String preMenu) {
		this.preMenu = preMenu;
	}
	@Override
	public String getAuthority() {
		return this.menuOption.toUpperCase().replace(" ", "-");
	}

}
