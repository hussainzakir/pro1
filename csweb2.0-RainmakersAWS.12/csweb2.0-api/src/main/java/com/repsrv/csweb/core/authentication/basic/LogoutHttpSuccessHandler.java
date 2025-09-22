package com.repsrv.csweb.core.authentication.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class LogoutHttpSuccessHandler implements LogoutSuccessHandler {
	protected final Logger logger = LoggerFactory.getLogger(LogoutHttpSuccessHandler.class);

	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication auth) throws IOException,
			ServletException {
		
            this.logger.debug("Basic auth logout successful");
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().flush();
		}
}
