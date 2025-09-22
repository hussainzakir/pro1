package com.repsrv.csweb.rest.utils;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.repsrv.csweb.core.authorization.model.SuperUserPermissions;
import com.repsrv.csweb.core.model.User;


public class CswebSecurityUtils {
	private static final Logger logger = LoggerFactory.getLogger(CswebSecurityUtils.class);

    
    public static String getBaseUrl(HttpServletRequest request) {
        String scheme = request.getScheme() + "://";
        String serverName = request.getServerName();
        String serverPort = (request.getServerPort() == 80) ? "" : ":" + request.getServerPort();
        String contextPath = request.getContextPath();
        return scheme + serverName + serverPort + contextPath;
      }
    
    /**
     * Return the User object 
     * @return
     */
    public static String getLoggedInUser() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    	return ((User)authentication.getPrincipal()).getUsername();
    }
    
    public static boolean isSuperAdmin() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	
    	return "Y".equalsIgnoreCase(((SuperUserPermissions)authentication.getDetails()).getSuperUser());
    }

}
