package com.careerly.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.careerly.common.dao.impl.CommonDao;
import com.careerly.common.page.PageBean;
import com.careerly.dao.dbmodel.UserInfo;
import com.careerly.exception.DAOException;
import com.careerly.utils.DaoUtils;



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
			Map<String, Object> searchMap = new HashMap<String, Object>();

			StringBuffer hql = new StringBuffer("FROM UserInfo WHERE 1=1 ");

			DaoUtils.setSearchParam(hql, page.getSearch(), searchMap);

			return this.findListByPage(hql.toString(), page, searchMap);
		} catch (Exception e) {
			String errorMsg = "find user list is error!";
			log.error(errorMsg, e);
			throw new DAOException(errorMsg);
		}
	}

}
