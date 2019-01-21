package com.hzh.crm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.hzh.crm.domain.Customer;

/**
 * 客户Dao接口
 * @author ken
 *
 */
public interface CustomerDao extends BaseDao<Customer>{

	Integer findCount(DetachedCriteria detachedCriteria);

	List<Customer> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);

	Customer findById(Long cust_id);

	List<Customer> findAll();

}
