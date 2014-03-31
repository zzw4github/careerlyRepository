package com.careerly.common.init;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.careerly.common.Constants;


@Component
public class SessionChecker implements HandlerInterceptor{
    Logger log = LoggerFactory.getLogger(SessionChecker.class);
    
    public boolean preHandle(HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object o){
    	try{
    	log.info("pre handle...");
    	log.info("uri:"+request.getRequestURI());
    	/**设置请求和响应对象的encoding**/
    	request.setCharacterEncoding(Constants.DEFAULT_ENCODING);
    	response.setCharacterEncoding(Constants.DEFAULT_ENCODING);
    	}catch(Exception e)
    	{
    		log.error("Error on invoking preHandle", e);
    	}
    	return true;
    }
    
    public void postHandle(HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse, Object o, org.springframework.web.servlet.ModelAndView modelAndView) throws Exception{
    	log.debug("post handle...");
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception{
    	log.debug("after completion...");
    }
}
