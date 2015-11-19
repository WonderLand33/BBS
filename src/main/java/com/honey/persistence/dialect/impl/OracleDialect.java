package com.honey.persistence.dialect.impl;


import com.honey.entity.extend.PageResult;
import com.honey.persistence.dialect.Dialect;

public class OracleDialect extends Dialect {

	@Override
	public String buildPageSql(String sql, PageResult<?> page) {
		 StringBuilder pageSql = new StringBuilder(100);
	        String beginrow = String.valueOf((page.getCurrentPageNo() - 1) * page.getPageSize());
	        String endrow = String.valueOf(page.getCurrentPageNo() * page.getPageSize());
	        pageSql.append("select * from ( select temp.*, rownum row_id from ( ");
	        pageSql.append(sql);
	        pageSql.append(" ) temp where rownum <= ").append(endrow);
	        pageSql.append(") where row_id > ").append(beginrow);
	     return pageSql.toString();
	}

}
