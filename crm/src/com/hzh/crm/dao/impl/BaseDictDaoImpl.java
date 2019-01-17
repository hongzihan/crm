package com.hzh.crm.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.hzh.crm.dao.BaseDictDao;
import com.hzh.crm.domain.BaseDict;

/**
 * 字典DAO的实现类
 * @author ken
 *
 */
public class BaseDictDaoImpl extends HibernateDaoSupport implements BaseDictDao {

	@Override
	// 根据类型编码查询字典数据
	public List<BaseDict> findByTypeCode(String dict_type_code) {
		List<BaseDict> list = (List<BaseDict>) this.getHibernateTemplate().find("from BaseDict where dict_type_code=?", dict_type_code);
		return list;
	}

}
