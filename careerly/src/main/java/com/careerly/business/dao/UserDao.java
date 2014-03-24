package com.careerly.business.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.careerly.business.dbmodel.UserInfo;
import com.careerly.common.dao.impl.CommonDao;
import com.careerly.common.page.PageBean;
import com.careerly.exception.DAOException;



/**   
* @Title: UserDao.java 
* @Package com.careerly.business.dao 
* @Description: 用户dao
* @author careerly
*/ 
@Repository("userDao")
public class UserDao extends CommonDao {
	
	Logger log = LoggerFactory.getLogger(UserDao.class);

	   /**
	 * @author careerly
	 * @Description: 查询所有的用户信息
	 * @returnType List<UserInfo>
	 * @throws
	 */ 
	public List<UserInfo> findAll() throws DAOException {
		return this.listAll(UserInfo.class);
	}
	
	
	/**
	 * @author careerly
	 * @Description: 分页查询user集合
	 * @returnType List<UserInfo>
	 * @throws
	 */ 
	public List<UserInfo> findListByPage(PageBean page) throws DAOException
	{
		try {
			StringBuffer hql = new StringBuffer("FROM UserInfo ");
			return this.findListByPage(hql.toString(), page, null);
		} catch (Exception e) {
			String errorMsg = "find user list is error!";
			log.error(errorMsg, e);
			throw new DAOException(errorMsg);
		}
	}

}
