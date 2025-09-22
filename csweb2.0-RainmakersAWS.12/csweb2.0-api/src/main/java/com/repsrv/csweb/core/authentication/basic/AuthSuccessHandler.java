package com.repsrv.csweb.core.authentication.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import flexjson.JSONSerializer;

@Component
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthSuccessHandler.class);
	private JSONSerializer userSerializer = new JSONSerializer();

	public AuthSuccessHandler() {
		super();
		this.initSerializers();
	}

	public void postAuthenticationSuccess(HttpServletResponse response,
			Authentication authentication) throws IOException {

		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate");

		PrintWriter writer = response.getWriter();

		writer.write(this.userSerializer.serialize(authentication));
		writer.flush();
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {

		this.postAuthenticationSuccess(response, authentication);
	}

	protected void processSavedRequest(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {
		try {
			super.onAuthenticationSuccess(request, response, authentication);
		} catch (ServletException | IOException e) {
			LOGGER.error("Error processing request {}", request.getPathInfo(), e);
		}
	}

	private void initSerializers() {
		List<String> includes = new LinkedList<>();

		// User class
		includes.add("principal.username");
		includes.add("principal.userDivisions");
		includes.add("principal.userDivisions.userGroup");
		includes.add("principal.userDivisions.division");
		includes.add("authorities");
		includes.add("authorities.menuOption");
		includes.add("details");
		includes.add("details.*");

		this.userSerializer.setIncludes(includes);
		this.userSerializer.setExcludes(Arrays.asList("*",".class","class","details.class"));
	}
}