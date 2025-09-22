package com.repsrv.csweb.core.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessagesUtil {

    private static MessageSource messageSource;
	
    public static String getMessage(String key) {
    	return MessagesUtil.messageSource.getMessage(key, new String[]{}, key, Locale.getDefault());
    }
    
    public static String getMessage(String key, String parm) {
    	return MessagesUtil.messageSource.getMessage(key, new String[]{parm}, key, Locale.getDefault());
    }
    
    public static String getMessage(String key, String [] parms) {
    	return MessagesUtil.messageSource.getMessage(key, parms, key, Locale.getDefault());
    }
    
    @Autowired
	private void setMessageSource( MessageSource messageSource) {
    	MessagesUtil.messageSource = messageSource;
	}
}
