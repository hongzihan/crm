package com.hzh.crm.service;

import org.hibernate.criterion.DetachedCriteria;

import com.hzh.crm.domain.LinkMan;
import com.hzh.crm.domain.PageBean;

/**
 * 联系人的Service
 * @author ken
 *
 */
public interface LinkManService {

	PageBean<LinkMan> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	void save(LinkMan linkMan);

	LinkMan findById(Long lkm_id);

	void update(LinkMan linkMan);

	void delete(LinkMan linkMan);

}
