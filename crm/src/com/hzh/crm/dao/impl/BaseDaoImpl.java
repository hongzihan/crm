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
		// 反射: 第一步获得Class
		Class clazz = this.getClass(); // 这里获取到的class是目前正在被调用的class,CustomerDaoImpl或LinkManDaoImpl
		// 通过查看JDK的API得知可以通过class获取父类的class(这里的Class已经传入泛型T)
		Type type = clazz.getGenericSuperclass(); // 参数化类型: BaseDaoImpl<Customer>，BaseDaoImpl<LinkMan>
		// 得到的type本身是一个参数化的类型，所以这里将type强转成参数化的类型
		ParameterizedType pType = (ParameterizedType) type;
		// 通过参数化类型获得实际类型参数: 得到一个实际类型参数的数组?Map<String,Integer>(这里的泛型有可能是Map,所以这里获取的是数组)
		Type[] types = pType.getActualTypeArguments();
		// 只获得第一个实际类型参数即可
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
