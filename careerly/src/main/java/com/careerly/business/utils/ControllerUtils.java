package com.careerly.business.utils;

import org.springframework.web.servlet.ModelAndView;

import com.careerly.common.page.PageBean;

/**   
* @Title: ControllerUtils.java 
* @Package com.careerly.business.utils 
* @Description: Controller公用处理
* @author careerly
*/ 
public class ControllerUtils {

	/**
	 * @author careerly
	 * @Description: page参数设置
	 * @returnType void
	 * @throws
	 */ 
	public static void setPageParams(PageBean pageBean,ModelAndView mv,String requestUrl)
	{
		pageBean.setUrl(requestUrl);
		mv.addObject("page",pageBean);
	}
	
}
