package com.hzh.crm.web.action;

import com.hzh.crm.domain.User;
import com.hzh.crm.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User> {

	private User user = new User();
	
	@Override
	public User getModel() {
		return user;
	}
	
	// 注入Service 
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	/**
	 * 用户注册的方法: regist
	 */
	public String regist() {
		userService.regist(user);
		return LOGIN;
	}
}
