package com.careerly.business.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.careerly.business.dbmodel.UserInfo;
import com.careerly.business.service.IUserService;
import com.careerly.common.page.PageBean;

/**   
* @Title: RoleController.java 
* @Package com.careerly.business.controller 
* @Description: 角色管理
* @author careerly
*/ 
@Controller
@RequestMapping(value = "/role")
public class RoleController {

	Logger log = LoggerFactory.getLogger(RoleController.class);
	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response,PageBean page) {
		ModelAndView mv = new ModelAndView("/user/list");
		try {
			List<UserInfo> list = this.userService.findListByPage(page);
			mv.addObject("list", list);
			page.setUrl("/user/list");
			mv.addObject("page",page);
		} catch (Exception e) {
			log.error("find user list is error!", e);
		}
		return mv;
	}

}
