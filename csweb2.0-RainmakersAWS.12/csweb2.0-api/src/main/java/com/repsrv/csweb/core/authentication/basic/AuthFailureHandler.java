package com.repsrv.csweb.core.authentication.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.repsrv.csweb.rest.exception.CswebAuthException;

@Slf4j
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler{

    private final ObjectMapper objectMapper=new ObjectMapper();

	@Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {

        response.setContentType("application/json");
	    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        Map<String, Object> resp = new HashMap<>();
        
        if(exception instanceof CswebAuthException) {
        	CswebAuthException e = (CswebAuthException) exception;
        	resp.put("error", e.getLoginErrorType());
        	resp.put("messages", e.getErrorMessages());
        } else {
        	resp.put("error", exception.getMessage());
        }

        log.error("Failed login:", exception);
        
        PrintWriter writer = response.getWriter();
        this.objectMapper.writeValue(writer, resp);
        writer.flush();
    }

}
