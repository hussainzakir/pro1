package com.repsrv.csweb.rest.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GlobalFilter implements Filter {

    private static Logger LOG = LoggerFactory.getLogger(GlobalFilter.class);

    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;

        //request.setAttribute("NOW", new Date());
        //request.setAttribute("YEAR", Calendar.getInstance().get(Calendar.YEAR));

        // Attributes a = getAttributes(request);
        //request.setAttribute("REFERRER", request.getHeader("Referer"));
        //request.setAttribute("REQ_URI", request.getRequestURI());
        //request.setAttribute("VERSION", ClearKermitUtils.safePropertyGet(runtimeProperties, "version", "Version Missing"));
        //request.setAttribute("BUILD_TIME", ClearKermitUtils.safePropertyGet(runtimeProperties, "build.time", "Build Time Missing"));

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        
        arg2.doFilter(arg0, arg1);

    }


    public void destroy() {
        // TODO Auto-generated method stub
    }


    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}
