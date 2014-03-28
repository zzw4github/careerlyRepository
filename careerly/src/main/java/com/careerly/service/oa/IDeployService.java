package com.careerly.service.oa;

import java.util.List;

import com.careerly.common.page.PageBean;
import com.careerly.exception.BusinessServiceException;



/**   
* @Title: IProcessDeployService.java 
* @Package com.careerly.business.service 
* @Description: activiti工作流部署服务类
* @author careerly
*/ 
public interface IDeployService {

	
	/**
	 * @author careerly
	 * @Description: 查询定义的流程列表
	 * @returnType List<Object>
	 * @throws
	 */ 
	public List<Object[]> findProcessList(PageBean pageBean) throws BusinessServiceException;
	
}
