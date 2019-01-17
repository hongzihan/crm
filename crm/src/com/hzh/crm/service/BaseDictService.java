package com.hzh.crm.service;

import java.util.List;

import com.hzh.crm.domain.BaseDict;

/**
 * 字典业务层的接口
 * @author ken
 *
 */
public interface BaseDictService {

	List<BaseDict> findByTypeCode(String dict_type_code);

}
