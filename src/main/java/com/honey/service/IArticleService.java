package com.honey.service;

import com.honey.entity.auto.Article;
import com.honey.entity.auto.ArticleMain;
import com.honey.entity.auto.Type;
import com.honey.entity.extend.ArticleVo;
import com.honey.entity.extend.PageResult;
import com.honey.entity.extend.SimpleArticleVo;

import java.util.List;
import java.util.Map;

/**
 * @description
 * @author: chenPeng
 * @date: 2015/8/31 16:13
 * Copyright © 2015/8/31 Shanghai Raxtone Software Co.,Ltd Allright Reserved
 */
public interface IArticleService {

    PageResult<SimpleArticleVo> findSimpleArticles(Map<String, Object> param, PageResult<SimpleArticleVo> page);

    ArticleVo findById(Integer id);

    List<Type> findTypes();

    int saveArticle(ArticleMain articleMain , Article article);

    int up(Integer aid);
}
