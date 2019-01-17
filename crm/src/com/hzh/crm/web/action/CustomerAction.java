package com.hzh.crm.web.action;

import com.hzh.crm.domain.Customer;
import com.hzh.crm.service.CustomerService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 客户Action类
 * @author ken
 *
 */
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

	private Customer customer = new Customer();
	
	@Override
	public Customer getModel() {
		// TODO Auto-generated method stub
		return customer;
	}
	
	private CustomerService customerService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	/**
	 * 跳转到新增客户页面的方法 saveUI
	 */
	public String saveUI() {
		// 查询字典数据
		return "saveUI";
	}
	
	/**
	 * 添加客户到数据库的方法 saveCustomer
	 */
	public String saveCustomer() {
		customerService.save(customer);
		return NONE;
	}
	
	

}
