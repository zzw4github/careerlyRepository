package com.careerly.service.oa;

import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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
	
	
	/**
	 * @author careerly
	 * @Description: 流程部署
	 * @returnType boolean
	 * @throws
	 */ 
	public boolean deploy(MultipartFile file)throws BusinessServiceException;
	
	
	
	/**
	 * @author careerly
	 * @Description: 读取工作流资源文件
	 * @returnType InputStream
	 * @param processDefinitionId(流程ID)，type资源类型，image or xml
	 * @throws
	 */ 
	public InputStream readResource(String processDefinitionId,String type)throws BusinessServiceException;
	
}
