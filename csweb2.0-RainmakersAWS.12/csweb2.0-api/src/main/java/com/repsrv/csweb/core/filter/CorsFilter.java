package com.repsrv.csweb.core.filter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CorsFilter extends OncePerRequestFilter{

    private static final Logger logger = LoggerFactory.getLogger(CorsFilter.class);

    private String allowMethods;
    private List<String> allowedOriginRegexString;
    private List<Pattern> allowedOriginRegex;
    private String defaultAllowedOrigin = "http://cora.invalid.site.com";
    private Integer maxAge;
    private String allowHeaders;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String path = request.getRequestURI();

        String origin = request.getHeader("Origin");
        String allow = (this.allowOrigin(origin)) ? origin : this.defaultAllowedOrigin;

        if(allow != null){
            response.setHeader("Access-Control-Allow-Origin", allow);
            response.setHeader("Access-Control-Allow-Credentials", "true");
            if(this.allowMethods != null){
                response.setHeader("Access-Control-Allow-Methods", this.allowMethods);
            }
            if(this.maxAge != null){
                response.setHeader("Access-Control-Max-Age", String.valueOf(this.maxAge));
            }
            if(this.allowHeaders != null){
                response.setHeader("Access-Control-Allow-Headers", this.allowHeaders);
            }
        }

        if("OPTIONS".equalsIgnoreCase(request.getMethod())){
            response.setStatus(HttpServletResponse.SC_OK);
        }else{
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
    }

    private boolean allowOrigin(String origin) {
        boolean rval = false;
        if (origin != null && this.allowedOriginRegex != null) {
            for (Iterator<Pattern> i = this.allowedOriginRegex.iterator(); i.hasNext() && !rval; ) {
                rval = (i.next().matcher(origin).matches());
            }
            if ( !rval ) {
                logger.info("Attempted use of unallowed Origin origin=["+origin+"]");
            }
        }
        return rval;
    }

    public void setAllowedOriginRegexString(List<String> allowedOriginRegexString) {
        this.allowedOriginRegexString = allowedOriginRegexString;
        if (allowedOriginRegexString != null) {
            this.allowedOriginRegex = new ArrayList<>(allowedOriginRegexString.size());
            for (String s : allowedOriginRegexString) {
                this.allowedOriginRegex.add(Pattern.compile(s));
            }
        } else {
            this.allowedOriginRegex = null;
        }
    }

    public void setAllowMethods(String allowMethods) {
        this.allowMethods = allowMethods;
    }

    public void setDefaultAllowedOrigin(String defaultAllowedOrigin) {
        this.defaultAllowedOrigin = defaultAllowedOrigin;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    public void setAllowHeaders(String allowHeaders) {
        this.allowHeaders = allowHeaders;
    }
}
