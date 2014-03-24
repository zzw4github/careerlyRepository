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
	 * 注：异常信息后面带有“*”表示为调用外部接口抛出异常
	 * 	     异常信息后面带有“&”表示为dao层抛出异常
	 */ 
	public static BusinessServiceException errorCodeAutoException(Exception e,String errorMsg)
 {
		String reErrorMsg = "";
		if (e instanceof BusinessServiceException) {
			reErrorMsg = StringUtils.isBlank(e.getMessage()) ? errorMsg : e
					.getMessage();
		} else if (e instanceof DAOException) {
			reErrorMsg = StringUtils.isBlank(e.getMessage()) ? errorMsg : (e
					.getMessage() + "&");
		} else if (e instanceof InterfaceException) {
			reErrorMsg = StringUtils.isBlank(e.getMessage()) ? errorMsg : (e
					.getMessage() + "*");
		} else {
			reErrorMsg = errorMsg;
		}
		return new BusinessServiceException(reErrorMsg, e);
	}
}
