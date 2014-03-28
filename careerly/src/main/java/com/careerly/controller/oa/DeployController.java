package com.careerly.controller.oa;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.careerly.common.page.PageBean;
import com.careerly.exception.OperateActionException;
import com.careerly.service.oa.IDeployService;
import com.careerly.utils.ControllerUtils;

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
			ControllerUtils.setPageParams(pageBean, mv, "/activiti-deploy/list");
		}
		return mv;
	}

}
