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

@Controller
@RequestMapping(value = "/user")
public class UserController {

	Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("aaaaaa");
		ModelAndView mv = new ModelAndView("/list");
		try {
			List<UserInfo> list = this.userService.findAll();
			mv.addObject("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

}
