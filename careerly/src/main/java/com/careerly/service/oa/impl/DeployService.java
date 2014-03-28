package com.careerly.service.oa.impl;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.careerly.common.page.PageBean;
import com.careerly.exception.BusinessServiceException;
import com.careerly.service.oa.IDeployService;

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

	@Autowired
    private  RepositoryService repositoryService;
	
	@Autowired
    private  RuntimeService runtimeService;

	@Autowired
    private  TaskService taskService;

	public List<Object[]> findProcessList(PageBean pageBean) throws BusinessServiceException{
		//objects中存放两个对象（ProcessDefinition 流程定义对象、Deployment流程部署对象）
		 List<Object[]> objects = new ArrayList<Object[]>();
		 
		/***获取流程定义查询器*/
		ProcessDefinitionQuery definitionQuery =repositoryService.createProcessDefinitionQuery();
		/***分页查询流程集合***/
		List<ProcessDefinition> processDefinitionList =CommonService.findListByPage(definitionQuery, "listPage", pageBean,"count");
		for (ProcessDefinition processDefinition : processDefinitionList) {
            String deploymentId = processDefinition.getDeploymentId();
            Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
            objects.add(new Object[]{processDefinition, deployment});
        }
		return objects;
	}
    
    
}
