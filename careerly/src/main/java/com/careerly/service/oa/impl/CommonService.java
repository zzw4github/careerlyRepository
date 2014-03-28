package com.careerly.service.oa.impl;

import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.careerly.common.page.PageBean;
import com.careerly.exception.BusinessServiceException;
import com.careerly.exception.tool.ErrorTool;
import com.careerly.tool.BeanUtils;

public class CommonService {

	static Logger log = LoggerFactory.getLogger(CommonService.class);
	
	/**
	 * @author careerly
	 * @Description: 分页查询activiti对象集合
	 * @returnType List<U>
	 * @throws
	 */ 
	public static <T> List<T> findListByPage(Object obj, String methodName,
			PageBean pageBean, String countMethodName)
			throws BusinessServiceException
	{
		List<T>  list = null;
		try {
			//分页查询集合
			Method  m = BeanUtils.findMethod(obj.getClass(), methodName, int.class,int.class);
			list = (List<T>) m.invoke(obj, pageBean.getStartNum(),pageBean.getPageSize());
			//查询总条数
			Method countMethod = BeanUtils.findMethod(obj.getClass(), countMethodName);
			int count = ((Long)countMethod.invoke(obj)).intValue();
			//设置总条数在分页框架中
			pageBean.setTotalRows(count);
			
		} catch (Exception e) {
			String errorMsg = "Error invoke on findListByPage!";
			log.error(errorMsg,e);
			throw ErrorTool.errorAutoException(e, errorMsg);
		}
		return list;
	} 
}
