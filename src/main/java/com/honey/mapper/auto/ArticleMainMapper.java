package com.honey.mapper.auto;

import com.honey.entity.auto.ArticleMain;

public interface ArticleMainMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleMain record);

    int insertSelective(ArticleMain record);

    ArticleMain selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleMain record);

    int updateByPrimaryKeyWithBLOBs(ArticleMain record);

    int updateByPrimaryKey(ArticleMain record);
}