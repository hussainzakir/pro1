package com.repsrv.csweb.core.authentication.basic.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class CswebBasicAuthorizationFilter extends
		AbstractAuthenticationProcessingFilter{

	public CswebBasicAuthorizationFilter(){
		super(new AntPathRequestMatcher("/auth/login", "POST"));
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response){

		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
				request.getParameter("username"),
				request.getParameter("password"));
		
		Authentication auth = this.getAuthenticationManager().authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(auth);
		 
		 return auth;
	}

	@Override
	@Autowired
	@Qualifier("authManager")
	public void setAuthenticationManager(
			AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
	}
}
