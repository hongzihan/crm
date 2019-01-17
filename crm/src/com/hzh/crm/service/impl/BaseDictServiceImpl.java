package com.hzh.crm.service.impl;

import java.util.List;

import com.hzh.crm.dao.BaseDictDao;
import com.hzh.crm.domain.BaseDict;
import com.hzh.crm.service.BaseDictService;

/**
 * 字典的业务层的实现类
 * @author ken
 *
 */
public class BaseDictServiceImpl implements BaseDictService {
	// 注入DAO
	private BaseDictDao baseDictDao;

	public void setBaseDictDao(BaseDictDao baseDictDao) {
		this.baseDictDao = baseDictDao;
	}

	@Override
	public List<BaseDict> findByTypeCode(String dict_type_code) {
		return baseDictDao.findByTypeCode(dict_type_code);
	}
}
