package com.hzh.crm.service;

import java.util.List;

import com.hzh.crm.domain.User;

/**
 * 用户管理的Service
 * @author ken
 *
 */
public interface UserService {

	void regist(User user);

	User login(User user);

	List<User> findAll();

}
