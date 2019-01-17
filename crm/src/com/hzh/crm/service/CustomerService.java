package com.hzh.crm.service;

import org.hibernate.criterion.DetachedCriteria;

import com.hzh.crm.domain.Customer;
import com.hzh.crm.domain.PageBean;

/**
 * 客户业务接口
 * @author ken
 *
 */
public interface CustomerService {

	void save(Customer customer);

	PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	Customer findById(Long cust_id);

	void delete(Customer customer);

}
