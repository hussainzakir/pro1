package com.repsrv.csweb.core.login.service;

import java.util.Collections;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.repsrv.csweb.core.authentication.as400.LoginError;
import com.repsrv.csweb.core.authentication.as400.UserLogin;
import com.repsrv.csweb.rest.exception.CswebAuthException;


@Slf4j
@Service("loginService")
public class LoginService {
	
	@Value("${login.host}")
	private String loginHost;

	public void performLogin(String userName, String password) {
		performLogin(userName, password, loginHost);
	}

	/**
	 * Invoked to validate user credentials
	 * 
	 * @throws MediatorException
	 */
	public void performLogin(String userName, String password, String system) throws CswebAuthException {
		UserLogin userLogin = null;
		log.info("Attempting user {} login to {}", userName, system);
		try {
			userLogin = new UserLogin();
				if (userLogin.checkLogin(system, userName, password)) {
					//no op for now
				}

				if (!userLogin.getLoginError().equals(LoginError.NO_ERRORS)) {
					throw new CswebAuthException(userLogin.getLoginError().name(), userLogin.getErrorMessages());
				}
		} catch (Exception e) {
			throw new CswebAuthException(e.getMessage(), userLogin!=null ? userLogin.getErrorMessages() : Collections.EMPTY_LIST);
		}
	}

	public void changePassword(String username, String newPassword, String oldPassword, String system) throws RuntimeException {
		try {
			UserLogin userLogin = new UserLogin();
			if (userLogin.changePassword(system, username, oldPassword, newPassword)) {
				// user.setLoggedin(true);
			}

			if (!userLogin.getLoginError().equals(LoginError.NO_ERRORS)) {
				StringBuilder sb = new StringBuilder();

				for (String msg : userLogin.getErrorMessages()) {
					sb.append(msg).append("\n");
				}

				throw new RuntimeException(sb.toString());
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}

	/**
	 * Invoked to log user out of the application
	 * 
	 * @throws MediatorException
	 */
	public void performLogout() throws RuntimeException {
		try {
			//UserBean user = SessionUtil.getUser();
			//user.setSessionId(null);
			//user.setLoggedin(false);
		} catch (Exception e) {
		}
	}

}