package com.careerly.common.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.careerly.common.dao.ICommonDao;
import com.careerly.common.page.PageBean;

/**
 * @Title: CommonDao.java
 * @Package com.careerly.common.dao.impl
 * @Description: dao公用处理类
 * @author careerly
 */
@Repository("commonDao")
public class CommonDao implements ICommonDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public Query getQuery(String hql) {
		return this.getSession().createQuery(hql);
	}

	public SQLQuery getSqlQuery(String sql) {
		return this.getSession().createSQLQuery(sql);
	}

	public <T> T save(T model) {

		this.getSession().save(model);
		return model;
	}

	public <T> void saveOrUpdate(T model) {

		this.getSession().saveOrUpdate(model);

	}

	public <T> void update(T model) {

		this.getSession().update(model);

	}

	public <T> void merge(T model) {

		this.getSession().merge(model);
	}

	public <T, PK extends Serializable> void delete(Class<T> entityClass, PK id) {

		this.getSession().delete(this.get(entityClass, id));
	}

	public <T> void delete(T model) {

		this.getSession().delete(model);

	}

	@SuppressWarnings("unchecked")
	public <T, PK extends Serializable> T get(Class<T> entityClass, PK id) {

		return (T) this.getSession().get(entityClass, id);
	}

	public <T> int countAll(Class<T> entityClass) {

		Criteria criteria = getSession().createCriteria(entityClass);
		criteria.setProjection(Projections.rowCount());
		return ((Long) criteria.uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> listAll(Class<T> entityClass) {
		Criteria criteria = getSession().createCriteria(entityClass);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> queryByHQL(String hql, Object... paramlist) {
		Query query = this.getQuery(hql);
		this.setHqlParameters(query, paramlist);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> queryByHQLWithLimit(String hql, int start, int amount,
			Object... paramlist) {
		Query query = this.getQuery(hql);
		this.setHqlParameters(query, paramlist);
		query.setFirstResult(start);
		query.setMaxResults(amount);
		return query.list();
	}

	public <T> int queryCountByFilter(Class<T> entityClass,
			Map<String, Object> filter) {
		Criteria criteria = this.getSession().createCriteria(entityClass);
		if (filter != null)
			criteria.add(Restrictions.allEq(filter));
		criteria.setProjection(Projections.rowCount());
		return ((Long) criteria.uniqueResult()).intValue();
	}

	public <T> List<T> findListByPage(String hql, PageBean page,
			Map<String, Object> searchMap) {
		int startIndex = hql.indexOf("from");
		if (startIndex == -1) {
			startIndex = hql.indexOf("FROM");
		}
		String hqlCount = "select count(*) " + hql.substring(startIndex);
		List<Object> list = (List<Object>) this.findListByHql(hqlCount, 0, 1,
				searchMap);
		if (list != null) {
			int count = ((Long) list.get(0)).intValue();
			page.setTotalRows(count);
		}
		return this.findListByHql(hql, page.getStartNum(), page.getPageSize(),
				searchMap);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findListByHql(String hql, int start, int max,
			Map<String, Object> searchMap) {
		Query query = getSession().createQuery(hql);
		if (searchMap != null && searchMap.size() > 0) {
			query.setProperties(searchMap);
		}
		query.setFirstResult(start);
		query.setMaxResults(max);
		return query.list();
	}

	private void setHqlParameters(Query query, Object[] paramlist) {
		if (paramlist != null) {
			int length = paramlist.length;
			for (int i = 0; i < length; i++) {
				query.setParameter(i, paramlist[i]);
			}
		}
	}

}
