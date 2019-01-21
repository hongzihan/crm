package com.hzh.crm.dao;

/**
 * 通用的DAO接口
 * @author ken
 *
 */
public interface BaseDao<T> {
	public void save(T t);
	public void update(T t);
	public void delete(T t);
}
