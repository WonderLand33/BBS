package com.honey.entity.extend;

public class PageSortBean {

	private SortEnum sortType = SortEnum.ASC;

	private String sortField;

	public PageSortBean(SortEnum sortType, String sortField){
		this.sortType = sortType;
		this.sortField = sortField;
	}


	public SortEnum getSortType() {
		return sortType;
	}

	public void setSortType(SortEnum sortType) {
		this.sortType = sortType;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

}
