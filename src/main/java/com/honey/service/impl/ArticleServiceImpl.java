package com.honey.service.impl;

import com.honey.entity.auto.Article;
import com.honey.entity.auto.ArticleMain;
import com.honey.entity.auto.Type;
import com.honey.entity.extend.ArticleVo;
import com.honey.entity.extend.PageResult;
import com.honey.entity.extend.SimpleArticleVo;
import com.honey.mapper.auto.ArticleMainMapper;
import com.honey.mapper.auto.ArticleMapper;
import com.honey.mapper.extend.IArticleDao;
import com.honey.service.IArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @description
 * @author: chenPeng
 * @date: 2015/8/31 16:15
 * Copyright © 2015/8/31 Shanghai Raxtone Software Co.,Ltd Allright Reserved
 */

@Service
public class ArticleServiceImpl implements IArticleService{

    @Resource
    private IArticleDao iArticleDao;
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ArticleMainMapper articleMainMapper;

    @Override
    public PageResult<SimpleArticleVo> findSimpleArticles(Map<String, Object> param, PageResult<SimpleArticleVo> page) {
        page.setData(iArticleDao.findByPage(param,page));
        return page;
    }

    @Override
    public ArticleVo findById(Integer id) {
        return iArticleDao.findById(id);
    }

    @Override
    public List<Type> findTypes() {
        return iArticleDao.findAllType();
    }

    @Override
    public int saveArticle(ArticleMain articleMain, Article article) {
        iArticleDao.insertArticle(article);
        articleMain.setAid(article.getId());//设置关联key
        articleMainMapper.insertSelective(articleMain);
        return  article.getId();
    }

    @Override
    public int up(Integer aid) {
        return iArticleDao.up(aid);
    }

}
