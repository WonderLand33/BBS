package com.honey.ctrl;

import com.google.common.collect.Maps;
import com.honey.entity.auto.Article;
import com.honey.entity.auto.ArticleMain;
import com.honey.entity.auto.Type;
import com.honey.entity.extend.ArticleVo;
import com.honey.entity.extend.PageResult;
import com.honey.entity.extend.SimpleArticleVo;
import com.honey.service.IArticleService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description
 * @author: chenPeng
 * @date: 2015/8/31 14:50
 * Copyright © 2015/8/31 Shanghai Raxtone Software Co.,Ltd Allright Reserved
 */

@Controller
@RequestMapping("")
public class ArticleCtrl extends BaseCtrl {

    private static final Logger log = LoggerFactory.getLogger(UserCtrl.class);

    @Resource
    private IArticleService iArticleService;


    @RequestMapping("getList.htm")
    public String getSimpleArticle(@RequestParam Map<String, Object> param, PageResult<SimpleArticleVo> page,Model model){

        Map<String,Object> result=new HashMap<String,Object>();
        PageResult<SimpleArticleVo> pageResult=iArticleService.findSimpleArticles(param, page);
        try {
            result.put("items",pageResult.getData());
            result.put("totalCount", pageResult.getTotalCount());
        } catch (Exception e) {
            log.info("[查询主页文章出问题了啦]  QAQ 不要乱来~");
            e.printStackTrace();
        }
        model.addAttribute("r", result);
        return "DL";
    }

    @RequestMapping("getArticle.json")
    @ResponseBody
    public ArticleVo getArticle(@RequestParam Integer id, Model model) {
        log.info("******** article id is {}", id);
        ArticleVo articleVo = new ArticleVo();
        try {
            articleVo = iArticleService.findById(id);
            model.addAttribute("i", articleVo);
        } catch (Exception e) {
            log.info("[查询文章出问题了啦]  QAQ 不要乱来~");
            e.printStackTrace();
        }
        return articleVo;
    }

    @RequestMapping("getTypes.json")
    @ResponseBody
    public List<Type> getTypes(){
        return iArticleService.findTypes();
    }


    public String validate(Map<String,String> map){

        if(!ctx().isLogin()) return "未登录阿,别乱来 qwq";

        //类型
        String tag = map.get("tag");
        String title = map.get("title");
        String message = map.get("message");
        if(StringUtils.isBlank(tag))
            return "请选择文章类型";
        if(StringUtils.isBlank(title))
            return "请输入文章标题";
        if(StringUtils.isBlank(message))
            return "请输入文章内容";

        return "";
    }

    @RequestMapping("add.json")
    @ResponseBody
    public Map<String,Object> saveArticle(@RequestParam Map<String,String> map){

        Map<String,Object> result = Maps.newHashMap();
        Date date = new Date();
        String errMsg = validate(map);
        int errCode = -1;
        if(StringUtils.isBlank(errMsg)){
          Integer mid = ctx().getUser().getId();
          log.info("++++++++Login user id : {}", mid);
          Article article = new Article();
            article.setMid(mid);
            article.setTitle(map.get("title"));
            article.setTypeId(Integer.valueOf(map.get("tag")));
            article.setCreateTime(date);
          ArticleMain articleMain = new ArticleMain();
            articleMain.setBody(map.get("message"));
            articleMain.setUa(map.get("ua"));
            articleMain.setCreateTime(date);

            //save
            int id = iArticleService.saveArticle(articleMain,article);
            errCode = 1;
            result.put("id",id);
        }

        result.put("errCode",errCode);
        result.put("errMsg",errMsg);
        result.put("errTime",new Date().getTime());

        return result;

    }

    @RequestMapping("aup.json")
    public void up(@RequestParam Integer aid){
        try{
            iArticleService.up(aid);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
