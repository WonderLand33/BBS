package com.honey.persistence.dialect;

import com.honey.entity.extend.PageResult;
import org.apache.commons.lang.StringUtils;


public abstract class Dialect {

	/**
	 * 按照数据库方言拼接分页查询语句
	 * @param sql sql语句
	 * @param page 分页条件
	 * @return 拼接后的SQL语句
	 */
	public abstract String buildPageSql(String sql, PageResult<?> page);
	
	/**
	 * 拼接查询条数的数据
	 * @param sql sql语句
	 * @return 查询条数的语句
	 */
	public String getCountSql(String sql){
		return "select count(1) " +
		"from (" + StringUtils.substringBefore(sql, "order by") + ") total";
	}
}
