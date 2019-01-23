package com.hzh.crm.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.hzh.crm.dao.UserDao;
import com.hzh.crm.domain.User;

/**
 * UserDao的实现类
 * @author ken
 *
 */
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

//	public UserDaoImpl(Class clazz) {
//		super(User.class);
//	}

	@Override
	// DAO中根据用户账号和密码进行查询
	public User login(User user) {
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code=? and user_password=?", user.getUser_code(),user.getUser_password());
		// 判断
		if(list.size()>0) {
			return list.get(0);
		} else {
			return null;
		}
	}

}
