package com.hzh.crm.dao;

import java.util.List;

import com.hzh.crm.domain.BaseDict;

/**
 * 字典DAO的接口
 * @author ken
 *
 */
public interface BaseDictDao {

	List<BaseDict> findByTypeCode(String dict_type_code);

}
