package com.careerly.business.service;

import java.util.List;

import com.careerly.business.dbmodel.UserInfo;
import com.careerly.common.page.PageBean;
import com.careerly.exception.BusinessServiceException;


public interface IUserService {

	/**
	 * @author careerly
	 * @Description: 查询所用的用户信息
	 * @returnType List<UserInfo>
	 * @throws
	 */ 
	public List<UserInfo> findListByPage(PageBean page) throws BusinessServiceException;
}
