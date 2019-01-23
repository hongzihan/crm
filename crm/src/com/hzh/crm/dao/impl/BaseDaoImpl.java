package com.hzh.crm.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.hzh.crm.dao.BaseDao;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	
	private Class clazz;
	
	// 通过建立构造器来获取class
	public BaseDaoImpl() {
		Class clazz = this.getClass();
		Type type = clazz.getGenericSuperclass();
		ParameterizedType pType = (ParameterizedType) type;
		Type[] types = pType.getActualTypeArguments();
		this.clazz = (Class) types[0];
	}

	@Override
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	@Override
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	@Override
	public T findById(Serializable id) {
		Object object = this.getHibernateTemplate().get(clazz, id);
		return (T) object;
	}

	@Override
	public Integer findCount(DetachedCriteria detachedCriteria) {
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if(list.size()>0) {
			return list.get(0).intValue();
		}
		return null;
	}

	@Override
	public List<T> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
		detachedCriteria.setProjection(null);
		List<T> list = (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, pageSize);
		return list;
	}

	@Override
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from " + clazz.getSimpleName());
	}

}
