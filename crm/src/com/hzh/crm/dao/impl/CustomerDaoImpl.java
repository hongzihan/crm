package com.hzh.crm.dao.impl;

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

}
