package com.hzh.crm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.hzh.crm.domain.LinkMan;

/**
 * 联系人DAO接口
 * @author ken
 *
 */
public interface LinkManDao extends BaseDao<LinkMan>{

	Integer findCount(DetachedCriteria detachedCriteria);

	List<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);

	LinkMan findById(Long lkm_id);
}
