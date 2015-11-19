package com.honey.persistence.dialect.impl;



import com.honey.entity.extend.PageResult;
import com.honey.entity.extend.PageSortBean;
import com.honey.persistence.dialect.Dialect;

import java.util.List;


public class MySqlDialect extends Dialect {

	@Override
	public String buildPageSql(String sql, PageResult<?> page) {
		StringBuilder pageSql = new StringBuilder(sql);
		int pageSize = page.getPageSize();
		int beginrow = (page.getCurrentPageNo() - 1) * page.getPageSize();
		List<PageSortBean> sortBeanList = page.getSortBeanList();
		if (sortBeanList != null
				&& sortBeanList.size() > 0 ){
			pageSql.append(" order by ");
			for (PageSortBean pageSortBean : sortBeanList){
				pageSql.append(" "+pageSortBean.getSortField()+" "+ pageSortBean.getSortType().getType());
			}
		}
		if (pageSize > 0){
			pageSql.append(" limit ");
			if (beginrow > 0) {
				pageSql.append(beginrow).append(",").append(pageSize);
			} else {
				pageSql.append(pageSize);
			}
		}
		return pageSql.toString();
	}

}
