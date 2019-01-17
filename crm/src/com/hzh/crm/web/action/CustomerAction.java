package com.hzh.crm.web.action;

import org.hibernate.criterion.DetachedCriteria;

import com.hzh.crm.domain.Customer;
import com.hzh.crm.domain.PageBean;
import com.hzh.crm.service.CustomerService;
import com.opensymphony.xwork2.ActionContext;
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
	
	// 注入Service
	private CustomerService customerService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	// 使用set方法的方式接收数据
	private Integer currPage = 1;
	private Integer pageSize = 3;

	public void setCurrPage(Integer currPage) {
		if(currPage == null) {
			currPage = 1;
		}
		this.currPage = currPage;
	}
	

	public void setPageSize(Integer pageSize) {
		if(pageSize == null) {
			pageSize = 3;
		}
		this.pageSize = pageSize;
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
	
	/**
	 * 分页查询客户的方法 findAll
	 */
	public String findAll() {
		// 接收参数: 分页参数
		
		// 最好使用DetachedCriteria对象(条件查询-带分页)
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		// 调用业务层查询
		PageBean<Customer> pageBean = customerService.findByPage(detachedCriteria,currPage,pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	

}
