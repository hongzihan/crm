package com.hzh.crm.web.action;

import java.util.Date;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

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
	
	// 接收数据
	private Date visit_begin_time;
	private Date visit_end_time;

	public void setVisit_begin_time(Date visit_begin_time) {
		this.visit_begin_time = visit_begin_time;
	}

	public void setVisit_end_time(Date visit_end_time) {
		this.visit_end_time = visit_end_time;
	}

	public Date getVisit_begin_time() {
		return visit_begin_time;
	}

	public Date getVisit_end_time() {
		return visit_end_time;
	}


	/**
	 * 查询拜访记录列表的方法 : finadAll
	 */
	public String findAll() {
		// 创建离线条件查询对象
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SaleVisit.class);
		// 设置条件
		if(visit_begin_time != null) {
			detachedCriteria.add(Restrictions.ge("visit_time", visit_begin_time));
		}
		if(visit_end_time != null) {
			detachedCriteria.add(Restrictions.le("visit_time", visit_end_time));
		}
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
	
	/**
	 * 客户拜访记录的保存方法: save
	 */
	public String save() {
		saleVisitService.save(saleVisit);
		return "saveSuccess";
	}
}
