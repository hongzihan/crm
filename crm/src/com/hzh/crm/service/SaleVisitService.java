package com.hzh.crm.service;

import org.hibernate.criterion.DetachedCriteria;

import com.hzh.crm.domain.PageBean;
import com.hzh.crm.domain.SaleVisit;

/**
 * 客户拜访记录的Service
 * @author ken
 *
 */
public interface SaleVisitService {

	PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	void save(SaleVisit saleVisit);

}
