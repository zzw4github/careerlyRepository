package com.careerly.common.init;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
/**   
* @Title: ServletContextUtil.java 
* @Package com.careerly.common 
* @Description: 获取servlet上下文环境
* @author careerly
*/ 
@Component
public class ServletContextUtil implements ServletContextAware {

	Logger log = LoggerFactory.getLogger(ServletContextUtil.class);

	private static ServletContext servletContext = null;

	public void setServletContext(ServletContext servletContext) {

		log.info(" info on invoking setServletContext ");
		this.servletContext = servletContext;
	}

	@PostConstruct
	private void init() {
		log.info("ServletContextUtil init start!");
		InitService.setPageCondition(servletContext);
		log.info("ServletContextUtil init end!");
	}
}
