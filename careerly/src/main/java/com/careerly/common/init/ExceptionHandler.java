package com.careerly.common.init;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**   
* @Title: ExceptionHandler.java 
* @Package com.careerly.exception 
* @Description: controller异常处理类
* @author careerly
*/ 
public class ExceptionHandler implements HandlerExceptionResolver{

	/**
	 * @author careerly
	 * @Description: 处理控制层抛出的异常信息
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		String errorMsg = ex.getMessage();
		ModelAndView  mv = new ModelAndView("/common/exception");
		mv.addObject("errorMsg", errorMsg);
		return mv;
	}
	
	
}
