package com.hzh.crm.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.hzh.crm.dao.LinkManDao;
import com.hzh.crm.domain.LinkMan;

import sun.awt.image.ImageWatched.Link;

/**
 * 联系人DAO的实现类
 * @author ken
 *
 */
public class LinkManDaoImpl extends BaseDaoImpl<LinkMan> implements LinkManDao {
	
	
	/**
	 * 由于父类BaseDaoImpl不存在无参构造，所以当前类的无参构造也无效(子类必须实现父类的构造),所以
	 * 在这里我们新建构造器，来调用父类的有参构造，以此来将获取需要的class
	 * @param clazz
	 */
//	public LinkManDaoImpl(Class clazz) {
//		super(LinkMan.class);
//	}

}
