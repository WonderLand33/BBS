package com.honey.entity.extend;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * @description
 * @author: chenPeng
 * @date: 2015/8/31 15:26
 * Copyright © 2015/8/31 Shanghai Raxtone Software Co.,Ltd Allright Reserved
 */

public class PageResult<T> implements Serializable {

    // private int pageSize;//每页条数
    //
    // private int pageIndex;//传的第几页
    //
    // private int maxPage;//最大的页数
    //
    // private int curIndex;
    //
    // private int startIndex;//开始条数
    //
    // private int totalCount;//总数

    /**
     *
     */
    private static final long serialVersionUID = -6669029513748896770L;

    public static int DEFAULT_PAGESIZE = 10;

    private int currentPageNo; // 当前页码

    private int pageSize; // 每页行数

    private long totalCount; // 总记录数

    private int totalPage; // 总页数

    private List<T> data;

    private List<PageSortBean> sortBeanList;

    /**
     * 是否超出最大页调整为最后一页
     */
    private boolean isOverToLastPage = false;

    public PageResult() {
        currentPageNo = 1;
        pageSize = DEFAULT_PAGESIZE;
        totalCount = 0;
        totalPage = 0;
    }

    public PageResult(Integer currentPageNo) {
        this.currentPageNo=currentPageNo!=null?currentPageNo:1;
        this.pageSize =  DEFAULT_PAGESIZE;
        totalCount = 0;
        totalPage = 0;
    }

    public PageResult(Integer currentPageNo, Integer pageSize) {
        this.currentPageNo=currentPageNo!=null?currentPageNo:1;
        this.pageSize = pageSize != null ? pageSize : DEFAULT_PAGESIZE;
        totalCount = 0;
        totalPage = 0;
    }

    public PageResult(Integer curPage,Integer pageSize,PageSortBean sort) {
        this.currentPageNo=curPage!=null?curPage:1;
        this.pageSize = pageSize != null ? pageSize : DEFAULT_PAGESIZE;
        totalCount = 0;
        totalPage = 0;
        if (sort != null) {
            this.sortBeanList= Lists.newArrayList(sort);
        }
    }

    public PageResult<T> sortBy(String field,SortEnum type) {
        if (this.sortBeanList == null) {
            this.sortBeanList=Lists.newArrayList(new PageSortBean(type,field));
        }else {
            this.sortBeanList.add(new PageSortBean(type, field));
        }
        return this;
    }
    public PageResult<T> sortBy(String field) {
        return sortBy(field, SortEnum.ASC);
    }

    /**
     * 总件数变化时，更新总页数并计算显示样式
     */
    private void refreshPage() {
        // 总页数计算
        totalPage = (int) (totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize + 1));
        // 防止超出最末页（浏览途中数据被删除的情况）
        if (currentPageNo > totalPage && totalPage != 0) {
            currentPageNo = totalPage;
        }
    }

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
        if (isOverToLastPage){
            refreshPage();
        }
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<PageSortBean> getSortBeanList() {
        return sortBeanList;
    }

    public void setSortBeanList(List<PageSortBean> sortBeanList) {
        this.sortBeanList = sortBeanList;
    }

    public boolean isOverToLastPage() {
        return isOverToLastPage;
    }

    public void setOverToLastPage(boolean isOverToLastPage) {
        this.isOverToLastPage = isOverToLastPage;
    }

}

