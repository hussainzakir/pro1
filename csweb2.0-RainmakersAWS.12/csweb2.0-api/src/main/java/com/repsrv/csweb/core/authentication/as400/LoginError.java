package com.repsrv.csweb.core.authentication.as400;

public enum LoginError
{
	NO_ERRORS(""), 
	SYSTEM_NAME_MISSING("System name missing."), 
	INVALID_SYSTEM_NAME( "Invalid system name &&&."), 
	INVALID_USER_ID("Invalid User ID &&&."), 
	USER_ID_MISSING("User ID missing."), 
	USER_ID_DISABLED("User profile &&& has been disabled."), 
	USER_ID_ABOUT_TO_BE_DISABLED("Incorrect password. Next invalid attempt will disable the &&& profile."), 
	PASSWORD_MISSING("Password missing."), 
	INVALID_PASSWORD("Incorrect password for the User ID &&& ."), 
	PASSWORD_EXPIRED("Password for the User ID &&& has expired."), 
	PASSWORD_ABOUT_TO_EXPIRE("Password for the User ID &&& will expire in &&&"), 
	OTHER_ERROR("&&&");
	
	public static final String MSG_ARG = "&&&";
	private String msg;

	private LoginError(String msg)
	{
		this.msg = msg;
	}

	public final String getMsg()
	{
		return this.msg;
	}

	public final String getMsg(String parm1)
	{
		return this.msg.replaceFirst(MSG_ARG, parm1);
	}

	public final String getMsg(String parm1, String parm2)
	{
		return this.msg.replaceFirst(MSG_ARG, parm1).replaceFirst(MSG_ARG, parm2);
	}
}
