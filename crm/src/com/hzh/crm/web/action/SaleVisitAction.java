package com.hzh.crm.web.action;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;

import com.hzh.crm.domain.PageBean;
import com.hzh.crm.domain.SaleVisit;
import com.hzh.crm.service.SaleVisitService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {
	
	// 模型驱动使用的对象
	private SaleVisit saleVisit = new SaleVisit();

	@Override
	public SaleVisit getModel() {
		// TODO Auto-generated method stub
		return saleVisit;
	}

	// 在Action中注入Service
	@Resource(name="saleVisitService")
	private SaleVisitService saleVisitService;
	
	// 接收分页数据
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
	 * 查询拜访记录列表的方法 : finadAll
	 */
	public String findAll() {
		// 创建离线条件查询对象
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SaleVisit.class);
		// 设置条件
		// 调用业务层
		PageBean<SaleVisit> pageBean = saleVisitService.findByPage(detachedCriteria,currPage,pageSize);
		// 存入值栈
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	/**
	 * 客户拜访记录页面跳转方法 : saveUI
	 */
	public String saveUI() {
		
		return "saveUI";
	}
}
