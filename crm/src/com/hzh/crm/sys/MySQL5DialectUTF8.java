package com.hzh.crm.sys;

import org.hibernate.dialect.MySQL5InnoDBDialect;

/**
 * 修改方言建表类型
 * @author ken
 *
 */
public class MySQL5DialectUTF8 extends MySQL5InnoDBDialect{
	@Override
	public String getTableTypeString() {
		return "ENGINE=InnoDB DEFAULT CHARSET=UTF8";
	}

}
