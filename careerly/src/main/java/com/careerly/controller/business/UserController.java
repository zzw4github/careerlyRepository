package com.careerly.controller.business;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.careerly.common.page.PageBean;
import com.careerly.common.vo.condition.SearchModel;
import com.careerly.dao.dbmodel.UserInfo;
import com.careerly.exception.OperateActionException;
import com.careerly.service.business.IUserService;
import com.careerly.utils.WebUtils;

/**   
* @Title: UserController.java 
* @Package com.careerly.business.controller 
* @Description: 用户管理
* @author careerly
*/ 
@Controller
@RequestMapping(value = "/user")
public class UserController {

	Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response,PageBean pageBean) throws OperateActionException{
		ModelAndView mv = new ModelAndView("/user/list");
		try {
			List<UserInfo> list = this.userService.findListByPage(pageBean);
			mv.addObject("list", list);
		} catch (Exception e) {
			log.error("find user list is error!", e);
			throw new OperateActionException(e.getMessage());
		}finally
		{
			WebUtils.setPageParams(pageBean, mv, "/user/list");
		}
		return mv;
	}

}
