package com.repsrv.csweb.core.authentication.as400;

import java.net.UnknownHostException;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.SignonEvent;
import com.ibm.as400.access.SignonHandlerAdapter;

/**
 * For all methods that return a boolean, a returned value of <code>true</code> indicates that the
 * sign-on should proceed. <code>False</code> indicates that the sign-on should not proceed, in
 * which case the system object will throw an AS400SecurityException with an error code indicating
 * what information is missing or incorrect.
 */
public class LoginSignonHandler extends SignonHandlerAdapter
{
	private UserLogin userLogin;

	public LoginSignonHandler(UserLogin userLogin)
	{
		this.userLogin = userLogin;
	}

	@Override
	public boolean connectionInitiated(SignonEvent event, boolean forceUpdate)
	{
		return true;
	}

	@Override
	public boolean passwordAboutToExpire(SignonEvent event, int arg1)
	{
		try
		{
			this.userLogin.setDaysUntilExpiration(arg1);
			this.userLogin.setError(LoginError.PASSWORD_ABOUT_TO_EXPIRE, ((AS400) event.getSource())
					.getUserId(), arg1 != 1 ? String.valueOf(arg1) + " days." : String.valueOf(arg1)
					+ " day.");
		}
		catch (Exception e)
		{
			this.userLogin.addExceptionMsg(e);
		}

		return true;
	}

	@Override
	public boolean passwordExpired(SignonEvent event)
	{
		try
		{
			this.userLogin.setError(LoginError.PASSWORD_EXPIRED, ((AS400) event.getSource()).getUserId());
		}
		catch (Exception e)
		{
			this.userLogin.addExceptionMsg(e);
		}

		return false;
	}

	@Override
	public boolean passwordIncorrect(SignonEvent event)
	{
		try
		{
			this.userLogin.setError(LoginError.INVALID_PASSWORD, ((AS400) event.getSource()).getUserId());
		}
		catch (Exception e)
		{
			this.userLogin.addExceptionMsg(e);
		}

		return false;
	}

	@Override
	public boolean passwordLengthIncorrect(SignonEvent event)
	{
		try
		{
			this.userLogin.setError(LoginError.INVALID_PASSWORD, ((AS400) event.getSource()).getUserId());
		}
		catch (Exception e)
		{
			this.userLogin.addExceptionMsg(e);
		}

		return false;
	}

	@Override
	public boolean passwordMissing(SignonEvent arg0)
	{
		try
		{
			this.userLogin.setError(LoginError.PASSWORD_MISSING);
		}
		catch (Exception e)
		{
			this.userLogin.addExceptionMsg(e);
		}

		return false;
	}

	@Override
	public boolean systemNameMissing(SignonEvent arg0)
	{
		try
		{
			this.userLogin.setError(LoginError.SYSTEM_NAME_MISSING);
		}
		catch (Exception e)
		{
			this.userLogin.addExceptionMsg(e);
		}

		return false;
	}

	@Override
	public boolean systemNameUnknown(SignonEvent event, UnknownHostException arg1)
	{
		try
		{
			this.userLogin.setError(LoginError.INVALID_SYSTEM_NAME, ((AS400) event.getSource())
					.getSystemName());
		}
		catch (Exception e)
		{
			this.userLogin.addExceptionMsg(e);
		}

		return false;
	}

	@Override
	public boolean userIdAboutToBeDisabled(SignonEvent event)
	{
		try
		{
			this.userLogin.setError(LoginError.USER_ID_ABOUT_TO_BE_DISABLED, ((AS400) event.getSource())
					.getUserId());
		}
		catch (Exception e)
		{
			this.userLogin.addExceptionMsg(e);
		}

		return false;
	}

	@Override
	public boolean userIdDefaultAlreadyAssigned(SignonEvent arg0, String arg1)
	{
		return true;
	}

	@Override
	public boolean userIdDisabled(SignonEvent event)
	{
		try
		{
			this.userLogin.setError(LoginError.USER_ID_DISABLED, ((AS400) event.getSource()).getUserId());
		}
		catch (Exception e)
		{
			this.userLogin.addExceptionMsg(e);
		}

		return false;
	}

	@Override
	public boolean userIdLengthIncorrect(SignonEvent event)
	{
		try
		{
			this.userLogin.setError(LoginError.INVALID_USER_ID, ((AS400) event.getSource()).getUserId());
		}
		catch (Exception e)
		{
			this.userLogin.addExceptionMsg(e);
		}

		return false;
	}

	@Override
	public boolean userIdMissing(SignonEvent arg0)
	{
		try
		{
			this.userLogin.setError(LoginError.USER_ID_MISSING);
		}
		catch (Exception e)
		{
			this.userLogin.addExceptionMsg(e);
		}

		return false;
	}

	@Override
	public boolean userIdUnknown(SignonEvent event)
	{
		try
		{
			this.userLogin.setError(LoginError.INVALID_USER_ID, ((AS400) event.getSource()).getUserId());
		}
		catch (Exception e)
		{
			this.userLogin.addExceptionMsg(e);
		}

		return false;
	}
}
