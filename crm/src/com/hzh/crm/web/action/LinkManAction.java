package com.hzh.crm.web.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.hzh.crm.domain.Customer;
import com.hzh.crm.domain.LinkMan;
import com.hzh.crm.domain.PageBean;
import com.hzh.crm.service.CustomerService;
import com.hzh.crm.service.LinkManService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {

	private LinkMan linkMan = new LinkMan();
	
	@Override
	public LinkMan getModel() {
		// TODO Auto-generated method stub
		return linkMan;
	}

	// 注入联系人Service
	private LinkManService linkManService;

	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	// 注入客户的Service 
	private CustomerService customerService;
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	// 分页参数
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
	 * 查询联系人列表的方法 findAll
	 */
	public String findAll() {
		// 创建离线条件查询
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class);
		// 设置条件
		// 调用业务层
		PageBean<LinkMan> pageBean = linkManService.findAll(detachedCriteria, currPage, pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	/**
	 * 联系人保存页面跳转方法: saveUI
	 */
	public String saveUI() {
		// 查询所有客户
		List<Customer> list = customerService.findAll();
		// 将list集合保存到值栈中
		ActionContext.getContext().getValueStack().set("list",list);
		return "saveUI";
	}
	
	/**
	 * 保存联系人的方法
	 * @return
	 */
	public String save() {
		// 调用业务层保存联系人
		linkManService.save(linkMan);
		return "saveSuccess";
	}
	
	/**
	 * 跳转到编辑页面的方法 edit
	 */
	public String edit() {
		// 查询某个联系人，查询所有客户。
		// 查询所有客户
		List<Customer> list = customerService.findAll();
		// 查询联系人
		linkMan = linkManService.findById(linkMan.getLkm_id());
		// 将list和联系人的对象带回到页面
		ActionContext.getContext().getValueStack().set("list", list);
		ActionContext.getContext().getValueStack().push(linkMan);
		return "editSuccess";
	}
	
	/**
	 * 修改联系人信息的方法 update
	 */
	public String update() {
		// 调用业务层的方法
		linkManService.update(linkMan);
		return "updateSuccess";
	}
}
