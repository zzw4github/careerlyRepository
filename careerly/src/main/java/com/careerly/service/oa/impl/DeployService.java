package com.careerly.service.oa.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.careerly.common.page.PageBean;
import com.careerly.exception.BusinessServiceException;
import com.careerly.exception.tool.ErrorTool;
import com.careerly.service.oa.IDeployService;
import com.careerly.utils.WorkflowUtils;

/**   
* @Title: DeployService.java 
* @Package com.careerly.service.oa.impl 
* @Description:部署流程
* 注：repositoryService（ 它提供了管理和控制发布包和流程定义的操作）
* 	
* @author careerly
*/ 
@Service("deployService")
@Transactional(rollbackFor=Exception.class)
public class DeployService implements IDeployService{
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	
	private static final String FILE_EXTENSION_ZIP = "zip";
	
	private static final String FILE_EXTENSION_BAR = "bar";
	
	private static final String FILE_EXTENSION_BPMN = "bpmn";
	
	private static final String FILE_EXTENSION_BMPN20 = "bpmn20.xml";
	
	@Value(value = "#{'${activiti.deploy.export_dir}'}")
	private String EXPORTDIR;//图片导入路径
	
	@Autowired
    private  RepositoryService repositoryService;
	

	public List<Object[]> findProcessList(PageBean pageBean) throws BusinessServiceException{
		//objects中存放两个对象（ProcessDefinition 流程定义对象、Deployment流程部署对象）
		 List<Object[]> objects = new ArrayList<Object[]>();
		 
		/***获取流程定义查询器*/
		ProcessDefinitionQuery definitionQuery =repositoryService.createProcessDefinitionQuery();
		
		/***分页查询流程集合***/
		List<ProcessDefinition> processDefinitionList =CommonService.findListByPage(definitionQuery, "listPage", pageBean,"count");
		
		for (ProcessDefinition processDefinition : processDefinitionList) {
			String deploymentId = processDefinition.getDeploymentId();
			Deployment deployment = repositoryService.createDeploymentQuery()
					.deploymentId(deploymentId).singleResult();
			objects.add(new Object[] { processDefinition, deployment });
		}
		return objects;
	}
	

	public boolean deploy(MultipartFile file) throws BusinessServiceException {
		try {
			
			String fileName = file.getOriginalFilename();// 获取部署文件的名称
			String extension = FilenameUtils.getExtension(fileName);// 获取文件的扩展名
			Deployment deployment = null;
			
			log.debug(fileName+" deploy start!");
			/*** 部署 **/
			if (FILE_EXTENSION_ZIP.equals(extension)
					|| FILE_EXTENSION_BAR.equals(extension)) {// zip、bar包部署
				ZipInputStream zipInputStream = new ZipInputStream(
						file.getInputStream());// 创建zip流
				deployment = repositoryService.createDeployment()
						.addZipInputStream(zipInputStream).deploy();
				
			} else if (FILE_EXTENSION_BPMN.equals(extension)
					|| FILE_EXTENSION_BMPN20.equals(extension)) {// bpmn、bpmn20.xml
				
				deployment = repositoryService.createDeployment()
						.addInputStream(fileName, file.getInputStream())
						.deploy();
			}
			log.debug(fileName+" deploy end!");
			
		if(deployment!=null) {
		   List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).list();

            for (ProcessDefinition processDefinition : list) {
                WorkflowUtils.exportDiagramToFile(processDefinition, EXPORTDIR);
            }
			return true;
		}
		
		return false;
			
		}catch(Exception e)
		{
			String errorMsg = "部署流程失败！";
			log.error(errorMsg, e);
			throw ErrorTool.errorAutoException(e, errorMsg);
		}
	}


	public InputStream readResource(String processDefinitionId, String type)
			throws BusinessServiceException {
		try {

			ProcessDefinition processDefinition = repositoryService
					.createProcessDefinitionQuery()
					.processDefinitionId(processDefinitionId).singleResult();
			String resourceName = "";
			if (type.equals("image")) {// image文件
				resourceName = processDefinition.getDiagramResourceName();
			} else if (type.equals("xml")) {// xml文件
				resourceName = processDefinition.getResourceName();
			}
			InputStream resourceAsStream = repositoryService
					.getResourceAsStream(processDefinition.getDeploymentId(),
							resourceName);
			return resourceAsStream;
		} catch (Exception e) {
			String errorMsg = "部署流程失败！";
			log.error(errorMsg, e);
			throw ErrorTool.errorAutoException(e, errorMsg);
		}
	}
	
    
}
