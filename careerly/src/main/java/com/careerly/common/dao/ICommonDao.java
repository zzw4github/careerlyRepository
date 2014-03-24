package com.careerly.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.careerly.common.page.PageBean;


public interface ICommonDao {
	
	/**
	 * @author careerly
	 * @Description: 获取session对象
	 * @returnType Session
	 * @throws
	 */ 
	public Session getSession();
	
	/**
	 * @author careerly
	 * @Description: 获取query对象
	 * @returnType Query
	 * @throws
	 */ 
	public Query getQuery(String hql);

	/**
	 * @author careerly
	 * @Description: 获取sqlquery对象
	 * @returnType SQLQuery
	 * @throws
	 */ 
	public SQLQuery getSqlQuery(String sql);

    /**
     * @author careerly
     * @Description:save model
     * @returnType T
     * @throws
     */ 
    public <T> T save(T model);
    
    /**
     * @author careerly
     * @Description: save或者update entity
     * @returnType void
     * @throws
     */ 
    public <T> void saveOrUpdate(T model);
    
    /**
     * @author careerly
     * @Description: update entity
     * @returnType void
     * @throws
     */ 
    public <T> void update(T model);
    
    /**
     * @author careerly
     * @Description: merge entity
     * @returnType void
     * @throws
     */ 
    public <T> void merge(T model);
    
    /**
     * @author careerly
     * @Description: delete entity
     * @returnType void
     * @throws
     */ 
    public <T, PK extends Serializable> void delete(Class<T> entityClass, PK id);
    
    /**
     * @author careerly
     * @Description: delete entity
     * @returnType void
     * @throws
     */ 
    public <T> void delete(T model);
    
    /**
     * @author careerly
     * @Description: get entity
     * @returnType T
     * @throws
     */ 
    public <T, PK extends Serializable> T get(Class<T> entityClass, PK id);
    
    /**
     * @author careerly
     * @Description: entity记录总条数
     * @returnType int
     * @throws
     */ 
    public <T> int countAll(Class<T> entityClass);
    
    /**
     * @author careerly
     * @Description: entity总条数
     * @returnType List<T>
     * @throws
     */ 
    public <T> List<T> listAll(Class<T> entityClass);
    
    /**
     * @author careerly
     * @Description: queryByHQL
     * @returnType List<T>
     * @throws
     */ 
    public <T> List<T> queryByHQL(String hql,final Object... paramlist);
    
    /**
     * @author careerly
     * @Description: queryByHQLWithLimit
     * @returnType List<T>
     * @throws
     */ 
    public <T> List<T> queryByHQLWithLimit(String hql,int start,int amount,final Object... paramlist);
    
    /**
     * @author careerly
     * @Description: 通过过滤条件查询总行数
     * @returnType int
     * @throws
     */ 
    public <T> int queryCountByFilter(Class<T> entityClass,Map<String,Object> filter);
    
   
    /**
     * @author careerly
     * @Description: 通过分页查询集合
     * @returnType Object
     * @throws
     */ 
    public <T>List<T> findListByPage(String hql,PageBean page,Map<String,Object> searchMap);
    
    
}
