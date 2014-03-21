package com.careerly.business.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.careerly.business.dbmodel.UserInfo;
import com.careerly.common.dao.impl.CommonDao;
import com.careerly.exception.DAOException;



/**   
* @Title: UserDao.java 
* @Package com.careerly.business.dao 
* @Description: 用户dao
* @author careerly
*/ 
@Repository("userDao")
public class UserDao extends CommonDao {

   /**
 * @author careerly
 * @Description: 查询所有的用户信息
 * @returnType List<UserInfo>
 * @throws
 */ 
public List<UserInfo> findAll() throws DAOException {
	   	return this.listAll(UserInfo.class);
   }

}
