package com.careerly.common.init;

import javax.servlet.ServletContext;

import com.careerly.common.Constants;
import com.careerly.common.vo.condition.PageCondition;

public class InitService {

	
	/**
	 * @author careerly
	 * @Description: 设置常用搜索条件
	 * @returnType void
	 * @throws
	 */ 
	public static void setPageCondition(ServletContext servletContext)
	{
		servletContext.setAttribute(Constants.PAGE_CONDITION,new PageCondition());
	}
}
