package com.hzh.crm.web.action;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.hzh.crm.domain.Customer;
import com.hzh.crm.domain.LinkMan;
import com.hzh.crm.domain.PageBean;
import com.hzh.crm.service.CustomerService;
import com.hzh.crm.service.LinkManService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

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
		if(linkMan.getLkm_name() != null && !"".equals(linkMan.getLkm_name())) {
			detachedCriteria.add(Restrictions.like("lkm_name", "%" + linkMan.getLkm_name() + "%"));
		}
		if(linkMan.getLkm_gender() != null && !"".equals(linkMan.getLkm_gender())) {
			detachedCriteria.add(Restrictions.eq("lkm_gender", linkMan.getLkm_gender()));
		}
		if(linkMan.getCustomer() != null) {
			if(linkMan.getCustomer().getCust_id()!=null && !"".equals(linkMan.getCustomer().getCust_id())) {
				detachedCriteria.add(Restrictions.eq("customer.cust_id", linkMan.getCustomer().getCust_id()));
				// 调用Service将指定的customer查出来后存入值栈
				Customer customer = customerService.findById(linkMan.getCustomer().getCust_id());
				ActionContext.getContext().getValueStack().push(customer);
			}
		}
		if(linkMan.getLkm_position() != null && !"".equals(linkMan.getLkm_position())) {
			detachedCriteria.add(Restrictions.eq("lkm_position", linkMan.getLkm_position()));
		}
		if(linkMan.getLkm_mobile() != null && !"".equals(linkMan.getLkm_mobile())) {
			detachedCriteria.add(Restrictions.eq("lkm_mobile", linkMan.getLkm_mobile()));
		}
		if(linkMan.getLkm_phone() != null && !"".equals(linkMan.getLkm_phone())) {
			detachedCriteria.add(Restrictions.eq("lkm_phone", linkMan.getLkm_phone()));
		}
		if(linkMan.getLkm_qq() != null && !"".equals(linkMan.getLkm_qq())) {
			detachedCriteria.add(Restrictions.eq("lkm_qq", linkMan.getLkm_qq()));
		}
		if(linkMan.getLkm_email() != null && !"".equals(linkMan.getLkm_email())) {
			detachedCriteria.add(Restrictions.eqOrIsNull("lkm_email", linkMan.getLkm_email()));
		}
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
	
	/**
	 * 删除联系人的方法 delete
	 */
	public String delete() {
		// 先查询
		linkMan = linkManService.findById(linkMan.getLkm_id());
		// 删除联系人
		linkManService.delete(linkMan);
		return "deleteSuccess";
	}
	
	/**
	 * 异步查询所有客户回显页面的方法：asyncFindAll
	 * @throws IOException 
	 */
	public String asyncFindAll() throws IOException {
		// 调用业务层进行查询所有的客户
		List<Customer> list = customerService.findAll();
		for (Customer customer : list) {
			System.out.println(customer);
		}
		// 将list转换为json
		/**
		 * JSONConfig: 转JSON的配置对象
		 * JSONArray: 将数组和list集合转成JSON
		 * JSONObject: 将对象和Map集合转成JSON
		 */
		// 通过jsonConfig来去除多余的数据
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"cust_phone","cust_mobile","cust_image"});
		// 配置对象过滤掉linkMans,baseDictSource,baseDictLevel,baseDictIndustry字段
		// 由于这四个字段会造成JSON死循环，所以在这里过滤掉(因为他们之间存在一对多或多对一的关系，导致多次查询)
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {
			   if(name.equals("linkMans") 
					   || name.equals("baseDictSource") 
					   || name.equals("baseDictLevel") 
					   || name.equals("baseDictIndustry")) {
				   return true;
			   } else {
				   return false;
			   }
			}                                                                                                                                                                                                                                                                                                                                                                                                              
		});
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		System.out.println(jsonArray.toString());
		// 将JSON打印到页面上
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		return NONE;
	}
}
