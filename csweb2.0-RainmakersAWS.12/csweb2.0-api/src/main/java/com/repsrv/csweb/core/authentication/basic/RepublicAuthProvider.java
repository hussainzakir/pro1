package com.repsrv.csweb.core.authentication.basic;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.repsrv.csweb.core.authorization.model.AuthResponse;
import com.repsrv.csweb.core.authorization.model.DivisionsInfo;
import com.repsrv.csweb.core.authorization.model.SuperUserPermissions;
import com.repsrv.csweb.core.authorization.service.AuthorizationService;
import com.repsrv.csweb.core.login.service.LoginService;
import com.repsrv.csweb.core.model.User;
import com.repsrv.csweb.core.model.UserAuthorization;

@Component("republicAuthProvider")
public class RepublicAuthProvider implements AuthenticationProvider {
 
	private static final Logger logger =  LoggerFactory.getLogger(RepublicAuthProvider.class);
	
	@Value("${mock.user}")
	private String mockUser = null;
	
    @Autowired(required = true)  
    @Qualifier(value = "loginService")  
	private LoginService loginService;

    @Autowired
    private AuthorizationService authService;
	
    @Override
    public Authentication authenticate(Authentication authentication) 
      throws AuthenticationException {
    	
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<UserAuthorization> auths = new ArrayList<>();
        if(StringUtils.isBlank(mockUser)) {
         logger.info("logging in user {} ",name);
         loginService.performLogin(name, password);

          AuthResponse authResponse = this.authService.getUserAuthDetail("BI", name);
        
         auths = this.authService.getUserAuthorizations(authResponse);
         if(auths != null && logger.isDebugEnabled()) {
        	 logger.debug("Logged in user {} authorities: ",name);
        	 for(UserAuthorization auth : auths) {
        		 logger.debug("{}", auth.getAuthority());
        	 }
         }
            SuperUserPermissions superPerms = this.authService.getSuperPerms(authResponse);
            
            List<DivisionsInfo> divisions = this.authService.getUserDivisions(authResponse);
            
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    new User(name, divisions), password, auths);
            token.setDetails(superPerms);
            
            logger.debug("auth for user {} ", authResponse);
            return token;
        } else {
            if (StringUtils.isNotBlank(password) && auths.isEmpty()) {
                auths.add(new UserAuthorization("MOCK-AUTH"));
            }
            return new UsernamePasswordAuthenticationToken(
                    new User(name, this.authService.getUserDivisions(new AuthResponse())), password, auths);
        }
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
