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
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

//	public CustomerDaoImpl(Class clazz) {
//		super(Customer.class);
//	}

}
