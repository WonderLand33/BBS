package com.honey.mapper.extend;

import com.honey.entity.auto.Article;
import com.honey.entity.auto.Type;
import com.honey.entity.extend.ArticleVo;
import com.honey.entity.extend.PageResult;
import com.honey.entity.extend.SimpleArticleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @description
 * @author: chenPeng
 * @date: 2015/8/31 16:03
 * Copyright © 2015/8/31 Shanghai Raxtone Software Co.,Ltd Allright Reserved
 */
public interface IArticleDao {


    /**
     * 分页查询文章标题
     * @param param
     * @param page
     * @return
     */
    List<SimpleArticleVo> findByPage(@Param("map") Map<String, Object> param, PageResult<SimpleArticleVo> page);

    /**
     * 根据ID查询文章
     * @param id
     * @return
     */
    ArticleVo findById(@Param("id") Integer id);

    /**
     * 查询所有分类
     * @return
     */
    List<Type> findAllType();

    void insertArticle(Article article);

    /**
     * 喜欢
     * @param aid
     * @return
     */
    int up(@Param("aid") Integer aid);

}
