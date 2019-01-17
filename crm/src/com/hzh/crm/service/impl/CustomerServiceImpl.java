package com.hzh.crm.service.impl;

import org.springframework.transaction.annotation.Transactional;

import com.hzh.crm.dao.CustomerDao;
import com.hzh.crm.domain.Customer;
import com.hzh.crm.service.CustomerService;

/**
 * 客户业务实现类
 * @author ken
 *
 */
@Transactional
public class CustomerServiceImpl implements CustomerService {
	private CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	// DAO层保存客户的方法
	public void save(Customer customer) {
		customerDao.save(customer);
	}
	
	
}
