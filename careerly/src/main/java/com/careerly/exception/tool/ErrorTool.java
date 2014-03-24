package com.careerly.exception.tool;



import org.apache.commons.lang.StringUtils;

import com.careerly.exception.BusinessServiceException;
import com.careerly.exception.DAOException;
import com.careerly.exception.InterfaceException;


/**   
* @Title: ErrorTool.java 
* @Package com.careerly.exception.tool 
* @Description: 业务异常转换
* @author careerly
*/ 
public class ErrorTool {
	
	
	/**
	 * @author careerly
	 * @Description: 业务异常转换
	 * @returnType BusinessServiceException
	 * @throws
	 */ 
	public static BusinessServiceException errorCodeAutoException(Exception e,String errorMsg)
 {
		BusinessServiceException rebex = null;
		if (e instanceof BusinessServiceException || e instanceof DAOException
				|| e instanceof InterfaceException) {
			if (StringUtils.isBlank(e.getMessage())) {
				rebex = new BusinessServiceException(errorMsg, e);
			} else {
				rebex = new BusinessServiceException(e.getMessage(), e);
			}
		} else {
			rebex = new BusinessServiceException(errorMsg, e);
		}
		return rebex;
	}
}
