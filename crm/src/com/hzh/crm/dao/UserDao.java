package com.hzh.crm.dao;

import com.hzh.crm.domain.User;

/**
 * 用户管理Dao的接口
 * @author ken
 *
 */
public interface UserDao extends BaseDao<User>{

	User login(User user);

}
