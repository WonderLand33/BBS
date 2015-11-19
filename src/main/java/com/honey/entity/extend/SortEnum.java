package com.honey.entity.extend;

/**
 * @description 排序规则
 * @author: chenPeng
 * @date: 2015/8/31 15:28
 * Copyright © 2015/8/31 Shanghai Raxtone Software Co.,Ltd Allright Reserved
 */
public enum SortEnum {

    DESC("desc"),ASC("asc");

    private String type;

    SortEnum(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
