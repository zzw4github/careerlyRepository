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
@Transactional(rollbackFor=Exception.class)
public class UserService implements IUserService {
	
	Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserDao userDao;
	
	public List<UserInfo> findListByPage(PageBean page)
			throws BusinessServiceException {
		try {
			UserInfo  u = new UserInfo();
			u.setUserName("测试");
			this.userDao.save(u);
			return this.userDao.findListByPage(page);
		} catch (Exception e) {
			String errorMsg = "查询用户信息失败！";
			log.error(errorMsg, e);
			throw ErrorTool.errorCodeAutoException(e, errorMsg);
		}
	}
   
}
