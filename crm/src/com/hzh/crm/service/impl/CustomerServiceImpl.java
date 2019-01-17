package com.hzh.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.hzh.crm.dao.CustomerDao;
import com.hzh.crm.domain.Customer;
import com.hzh.crm.domain.PageBean;
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

	@Override
	public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<Customer> pageBean = new PageBean<Customer>();
		// 封装当前的页数
		pageBean.setCurrPage(currPage);
		// 封装每页显示的记录数
		pageBean.setPageSize(pageSize);
		// 封装总记录数
		// 调用Dao
		Integer totalCount = customerDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		// 封装总页数
		Double tc = totalCount.doubleValue();
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		// 封装每页显示数据的集合
		Integer begin = (currPage - 1)*pageSize;
		List<Customer> list = customerDao.findByPage(detachedCriteria,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}
	
	
}
