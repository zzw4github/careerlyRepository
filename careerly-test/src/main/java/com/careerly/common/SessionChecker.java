package com.careerly.common;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class SessionChecker implements HandlerInterceptor{
    Logger logger = LoggerFactory.getLogger(SessionChecker.class);
    
    public boolean preHandle(HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object o){
    	System.out.println(request.getRequestURL()+"------------------");
    	return true;
    }
    
    public void postHandle(HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse, Object o, org.springframework.web.servlet.ModelAndView modelAndView) throws Exception{
        logger.debug("post handle...");
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception{
        logger.debug("after completion...");
    }
}
