package com.careerly.service.business;

import java.util.List;

import com.careerly.common.page.PageBean;
import com.careerly.dao.dbmodel.UserInfo;
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
