package com.hzh.crm.service.impl;

import org.springframework.transaction.annotation.Transactional;

import com.hzh.crm.dao.UserDao;
import com.hzh.crm.domain.User;
import com.hzh.crm.service.UserService;
import com.hzh.crm.utils.MD5Utils;

/**
 * 用户管理的Service实现类
 * @author ken
 *
 */
@Transactional
public class UserServiceImpl implements UserService {

	// 注入Dao
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	// 业务层注册用户的方法
	public void regist(User user) {
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		user.setUser_state("1");
		userDao.save(user);
	}

}