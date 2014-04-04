package com.careerly.controller.oa;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.repository.ProcessDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.careerly.common.page.PageBean;
import com.careerly.exception.OperateActionException;
import com.careerly.service.oa.IDeployService;
import com.careerly.tool.JsonUtils;
import com.careerly.utils.WebUtils;

/**   
* @Title: DeployController.java 
* @Package com.careerly.business.controller 
* @Description: activiti工作流程部署controlller
* @author careerly
*/ 
@Controller
@RequestMapping(value = "/activiti-deploy")
public class DeployController {

	Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IDeployService deployService;
	
	/**
	 * @author careerly
	 * @Description:查询部署流程集合
	 * @returnType ModelAndView
	 * @throws
	 */ 
	@RequestMapping(value = "/list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response,PageBean pageBean) throws OperateActionException{
		ModelAndView mv = new ModelAndView("/activiti/deploy/list");
		try {
			List<Object[]> list =this.deployService.findProcessList(pageBean);
			mv.addObject("list", list);
		} catch (Exception e) {
			log.error("Error on invoking deploy list!", e);
			throw new OperateActionException(e.getMessage());
		}finally
		{
			WebUtils.setPageParams(pageBean, mv, "/activiti-deploy/list");
		}
		return mv;
	}
	
	
	
	 /**
	 * @author careerly
	 * @Description: 部署activiti工作流程
	 * @returnType String
	 * @throws
	 */ 
	@RequestMapping(value = "/deploy")
	public String deploy(@RequestBody MultipartFile file,HttpServletResponse response) {
		String msg = "部署流程成功！";
		boolean status = true;
		try {
			this.deployService.deploy(file);
		} catch (Exception e) {
			status = false;//执行异常时，状态为false。
			msg = e.getMessage();//异常消息
			log.error(e.getMessage(), e);
		} finally {
			WebUtils.renderJson(response, JsonUtils.jsonMsg(status, msg));
		}
		return null;
	}
	
	
	 /**
     * 读取资源，通过部署ID
     * @param processDefinitionId 流程定义
     * @param resourceType        资源类型(xml|image)
     * @throws Exception
     */
    @RequestMapping(value = "/resource/read/{id}/{type}")
    public void readResource(@PathVariable String id,@PathVariable String type,HttpServletResponse response) throws Exception {
    	try {
    		InputStream resourceAsStream = this.deployService.readResource(id, type);
            byte[] b = new byte[1024];
            int len = -1;
            while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
                response.getOutputStream().write(b, 0, len);
            }
		} catch (Exception e) {
			log.error("Error on invoking readResource!", e);
			throw new OperateActionException(e.getMessage());
		}
    	
    }

}
