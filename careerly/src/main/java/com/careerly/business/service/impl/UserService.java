package com.careerly.business.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.careerly.business.dao.UserDao;
import com.careerly.business.dbmodel.UserInfo;
import com.careerly.business.service.IUserService;
import com.careerly.common.page.PageBean;
import com.careerly.exception.BusinessServiceException;
import com.careerly.exception.tool.ErrorTool;

@Service("userService")
@Transactional
public class UserService implements IUserService {
	
	Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserDao userDao;
	
	public List<UserInfo> findListByPage(PageBean page)
			throws BusinessServiceException {
		try {
			return this.userDao.findListByPage(page);
		} catch (Exception e) {
			String errorMsg = "查询用户信息失败！";
			log.error(errorMsg, e);
			throw ErrorTool.errorCodeAutoException(e, errorMsg);
		}
	}
   
}
