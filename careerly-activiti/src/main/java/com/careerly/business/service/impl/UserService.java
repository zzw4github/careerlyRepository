package com.careerly.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.careerly.business.dao.UserDao;
import com.careerly.business.dbmodel.UserInfo;
import com.careerly.business.service.IUserService;
import com.careerly.exception.BusinessServiceException;
import com.careerly.exception.tool.ErrorTool;

@Service("userService")
@Transactional
public class UserService implements IUserService {

	@Autowired
	private UserDao userDao;
	
	public List<UserInfo> findAll() throws BusinessServiceException {
		try{
		return this.userDao.findAll();
		}catch(Exception e)
		{
			throw ErrorTool.errorCodeAutoException(e, "查询用户信息失败！");
		}
	}
   
}
