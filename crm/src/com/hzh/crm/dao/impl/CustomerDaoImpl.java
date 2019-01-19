package com.hzh.crm.dao.impl;

import java.util.List;

import javax.persistence.criteria.From;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.hzh.crm.dao.CustomerDao;
import com.hzh.crm.domain.Customer;

/**
 * 客户Dao层实现
 * @author ken
 *
 */
public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

	@Override
	// DAO层保存客户的方法
	public void save(Customer customer) {
		System.out.println(customer);
		this.getHibernateTemplate().save(customer);
	}

	@Override
	// DAO中带条件统计个数的方法
	public Integer findCount(DetachedCriteria detachedCriteria) {
		// select count(*) from xxx where 条件;
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if(list.size()>0) {
			return list.get(0).intValue();
		}
		return null;
	}

	@Override
	// DAO中分页查询客户的方法
	public List<Customer> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
		detachedCriteria.setProjection(null);
		return (List<Customer>) this.getHibernateTemplate().findByCriteria(detachedCriteria,begin,pageSize);
	}

	@Override
	// DAO中根据ID查询客户的方法
	public Customer findById(Long cust_id) {
		return this.getHibernateTemplate().get(Customer.class, cust_id);
	}

	@Override
	// DAO中删除客户的方法
	public void delete(Customer customer) {
		this.getHibernateTemplate().delete(customer);
	}

	@Override
	// DAO中修改客户的方法
	public void update(Customer customer) {
		this.getHibernateTemplate().update(customer);
	}

	@Override
	// DAO中查询所有客户的方法
	public List<Customer> findAll() {
		List<Customer> list = (List<Customer>) this.getHibernateTemplate().find("From Customer");
		return list;
	}

}
