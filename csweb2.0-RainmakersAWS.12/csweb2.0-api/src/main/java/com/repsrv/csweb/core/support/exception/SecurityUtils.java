package com.repsrv.csweb.core.support.exception;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.repsrv.csweb.core.model.User;

public class SecurityUtils {

	public static String getLoggedInUser() {
		Authentication authentication = getLoggedInUserAuthentication();
		if (authentication != null && authentication.getPrincipal()!=null) {
			User user = (User)authentication.getPrincipal();
			String currentUserName = user.getUsername();
			return currentUserName == null ? null : currentUserName.toUpperCase();
		}
		return null;
	}

	public static Authentication getLoggedInUserAuthentication() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication instanceof AnonymousAuthenticationToken)
			return null;
		else
			return authentication;
	}

}
