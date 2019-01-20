package com.hzh.crm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.hzh.crm.domain.LinkMan;

/**
 * 联系人DAO接口
 * @author ken
 *
 */
public interface LinkManDao {

	Integer findCount(DetachedCriteria detachedCriteria);

	List<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);

	void save(LinkMan linkMan);

	LinkMan findById(Long lkm_id);

	void update(LinkMan linkMan);

}
