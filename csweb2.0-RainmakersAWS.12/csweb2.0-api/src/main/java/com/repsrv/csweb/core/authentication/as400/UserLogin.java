package com.repsrv.csweb.core.authentication.as400;


	import java.util.ArrayList;
	import java.util.List;

	import com.ibm.as400.access.AS400;

	/**
	 * This class allows to check the user ID and password and, if required, to change the user's
	 * password.
	 * <p>
	 * If any error occurred during the execution of <code>login()</code> or
	 * <code>changePassword()</code> method, the <code>errorCode</code> is initialized with the
	 * appropriate error code and error messages are written into the <code>errorMessages</code> list.
	 * Error code and messages can be retrieved by the calling class by executing
	 * <code>getErrorCode()</code> and <code>GetErrorMessages()</code> methods.
	 * <p>
	 * Possible error codes are:
	 * <ul>
	 * <li>NO_ERRORS
	 * <li>SYSTEM_NAME_MISSING
	 * <li>INVALID_SYSTEM_NAME
	 * <li>INVALID_USER_ID
	 * <li>USER_ID_MISSING
	 * <li>USER_ID_DISABLED
	 * <li>USER_ID_ABOUT_TO_BE_DISABLED
	 * <li>PASSWORD_MISSING
	 * <li>INVALID_PASSWORD
	 * <li>PASSWORD_EXPIRED
	 * <li>PASSWORD_ABOUT_TO_EXPIRE
	 * <li>OTHER_ERROR
	 * </ul>
	 * 
	 */
	public class UserLogin
	{
		private int daysUntilExpiration;
		private List<String> errorMessages;
		private LoginError loginError;
		private LoginSignonHandler signonHandler;

		public UserLogin()
		{
			this.signonHandler = new LoginSignonHandler(this);
		}

		/**
		 * Returns the number of days before password expiration to start warning the user.
		 * 
		 * @return
		 */
		public static final int getPasswordExpirationWarningDays()
		{
			return AS400.getPasswordExpirationWarningDays();
		}

		/**
		 * Sets the number of days before password expiration to warn the user.
		 * 
		 * @param passwordExpirationWarningDays
		 *            The number of days before expiration to start the warning. Set to -1 to turn off
		 *            warning.
		 */
		public static final void setPasswordExpirationWarningDays(int passwordExpirationWarningDays)
		{
			AS400.setPasswordExpirationWarningDays(passwordExpirationWarningDays);
		}

		/**
		 * This method changes the user profile's password.
		 * 
		 * @param system
		 *            The system name.
		 * @param user
		 *            The user profile.
		 * @param oldPassword
		 *            The old user profile's password.
		 * @param newPassword
		 *            The new user's profile password.
		 * @return <code>True</code> if the password change operation is successful, otherwise
		 *         <code>false</code>.
		 */
		public boolean changePassword(String system, String user, String oldPassword, String newPassword)
		{
			boolean success = false;
			this.daysUntilExpiration = 0;
			this.loginError = LoginError.NO_ERRORS;
			this.errorMessages = new ArrayList<>();
			AS400 as400 = new AS400();

			try
			{
				as400.setSignonHandler(this.signonHandler);
				as400.setSystemName(system);
				as400.setUserId(user);
				as400.changePassword(oldPassword, newPassword);
				success = true;
			}
			catch (Exception e)
			{
				if (this.loginError == LoginError.NO_ERRORS)
				{
					this.loginError = LoginError.OTHER_ERROR;
					this.errorMessages.add(e.getMessage());
				}
			}

			try
			{
				as400.disconnectAllServices();
			}
			catch (Exception e1)
			{}

			return success;
		}

		/**
		 * This method validates the user ID and password. If any errors occur during the execution, the
		 * method stores the error code in <code>errorCode</code> field and writes messages into the
		 * <code>errorMessages</code> list. The examples of error are incorrect password, disabled user
		 * ID, expired password, etc.
		 * 
		 * @param system
		 *            The system name.
		 * @param user
		 *            The user profile.
		 * @param password
		 *            The user profile's password.
		 * @return <code>True</code> if user can login with the provided User ID and password, otherwise
		 *         <code>false</code>.
		 */
		public boolean checkLogin(String system, String user, String password)
		{
			boolean success = false;
			this.daysUntilExpiration = 0;
			this.loginError = LoginError.NO_ERRORS;
			this.errorMessages = new ArrayList<String>();
			AS400.setDefaultSignonHandler(this.signonHandler);
			AS400 as400 = new AS400();

			try
			{
				
				as400.setSystemName(system);
				as400.setUserId(user);
				as400.setPassword(password);
				as400.connectService(AS400.SIGNON);
				success = true;
			}
			catch (Exception e)
			{
				if (this.loginError == LoginError.NO_ERRORS)
				{
					this.loginError = LoginError.OTHER_ERROR;
					this.errorMessages.add(e.getMessage());
				}
			}

			try
			{
				as400.disconnectAllServices();
			}
			catch (Exception e1)
			{}

			return success;
		}

		/**
		 * Returns the number of days before password expiration. The value is initialized during the
		 * login event by the CustomSignonHandler class, when it discovers that the password is within a
		 * few days of expiring. The value is set to 0 at the beginning of <code>login()</code> and
		 * <code>changePassword()</code> methods.
		 * 
		 * @return Number of days before password expiration.
		 */
		public final int getDaysUntilExpiration()
		{
			return this.daysUntilExpiration;
		}

		/**
		 * Returns error messages, which occurred during the last execution of <code>login()</code> or
		 * <code>changePassword()</code> method.
		 * 
		 * @return
		 */
		public final List<String> getErrorMessages()
		{
			return this.errorMessages;
		}

		public final LoginError getLoginError()
		{
			return this.loginError;
		}

		void addExceptionMsg(Exception e)
		{
			if (this.loginError == LoginError.NO_ERRORS)
			{
				this.loginError = LoginError.OTHER_ERROR;
			}

			this.errorMessages.add(e.getMessage());
		}

		void setDaysUntilExpiration(int daysUntilExpiration)
		{
			this.daysUntilExpiration = daysUntilExpiration;
		}

		void setError(LoginError error)
		{
			try
			{
				addErrorMsg(error, error.getMsg());
			}
			catch (Exception e)
			{}
		}

		void setError(LoginError error, String arg)
		{
			try
			{
				addErrorMsg(error, error.getMsg(arg));
			}
			catch (Exception e)
			{e.printStackTrace();}
		}

		void setError(LoginError error, String arg1, String arg2)
		{
			try
			{
				addErrorMsg(error, error.getMsg(arg1, arg2));
			}
			catch (Exception e)
			{}
		}

		private void addErrorMsg(LoginError error, String msg)
		{
			this.loginError = error;
			this.errorMessages.add(msg);
		}
	}