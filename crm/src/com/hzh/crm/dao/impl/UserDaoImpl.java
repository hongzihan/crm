package com.hzh.crm.dao.impl;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.hzh.crm.dao.UserDao;
import com.hzh.crm.domain.User;

/**
 * UserDao的实现类
 * @author ken
 *
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Override
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

}
