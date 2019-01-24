package com.hzh.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.hzh.crm.dao.SaleVisitDao;
import com.hzh.crm.domain.PageBean;
import com.hzh.crm.domain.SaleVisit;
import com.hzh.crm.service.SaleVisitService;

/**
 * 客户拜访记录Service的实现类
 * @author ken
 *
 */
@Transactional
public class SaleVisitServiceImpl implements SaleVisitService{
	// 注入客户拜访记录的DAO
	@Resource(name="saleVisitDao")
	private SaleVisitDao saleVisitDao;

	@Override
	// 业务层分页显示拜访记录的方法
	public PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<SaleVisit> pageBean = new PageBean<SaleVisit>();
		// 设置当前页数
		pageBean.setCurrPage(currPage);
		// 设置每页显示记录数
		pageBean.setPageSize(pageSize);
		// 设置总记录数
		Integer totalCount = saleVisitDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		double tc = totalCount;
		Double num = Math.ceil(tc);
		pageBean.setTotalPage(num.intValue());
		// 设置每页显示数据的集合
		Integer begin = (currPage - 1) * pageSize;
		List<SaleVisit> list = saleVisitDao.findByPage(detachedCriteria, begin, pageSize);
		pageBean.setList(list);
		return pageBean;
	}
}
