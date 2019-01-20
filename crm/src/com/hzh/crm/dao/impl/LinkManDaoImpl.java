package com.hzh.crm.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.hzh.crm.dao.LinkManDao;
import com.hzh.crm.domain.LinkMan;

/**
 * 联系人DAO的实现类
 * @author ken
 *
 */
public class LinkManDaoImpl extends HibernateDaoSupport implements LinkManDao {

	@Override
	public Integer findCount(DetachedCriteria detachedCriteria) {
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if(list.size() > 0) {
			return list.get(0).intValue();
		}
		return null;
	}

	@Override
	public List<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
		detachedCriteria.setProjection(null);
		List<LinkMan> list = (List<LinkMan>) this.getHibernateTemplate().findByCriteria(detachedCriteria,begin,pageSize);
		return list;
	}

	@Override
	// DAO中保存联系人的方法
	public void save(LinkMan linkMan) {
		this.getHibernateTemplate().save(linkMan);
	}

	@Override
	// DAO中根据id查找联系人的方法
	public LinkMan findById(Long lkm_id) {
		LinkMan linkMan = (LinkMan) this.getHibernateTemplate().get(LinkMan.class, lkm_id);
		return linkMan;
	}

	@Override
	// DAO中更新联系人的方法
	public void update(LinkMan linkMan) {
		this.getHibernateTemplate().update(linkMan);
	}

	@Override
	// DAO中删除联系人的方法
	public void delete(LinkMan linkMan) {
		this.getHibernateTemplate().delete(linkMan);
	}
	
}
