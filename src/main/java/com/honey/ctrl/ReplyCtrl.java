package com.honey.ctrl;

import com.google.common.collect.Maps;
import com.honey.entity.auto.Reply;
import com.honey.entity.extend.ReplyVo;
import com.honey.service.IReplyService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description
 * @author: chenPeng
 * @date: 2015/9/10 14:43
 * Copyright © 2015/9/10 Shanghai Raxtone Software Co.,Ltd Allright Reserved
 */

@Controller
public class ReplyCtrl extends BaseCtrl{

    Logger logger = LoggerFactory.getLogger(ReplyCtrl.class);

    @Resource
    private IReplyService iReplyService;

      @RequestMapping("message.json")
      @ResponseBody
      public List<ReplyVo> findMessage(@RequestParam Integer aid){
          logger.debug("reply id is {}",aid);
          return iReplyService.findReplysByArticle(aid);
      }

    @RequestMapping("reply.json")
    @ResponseBody
    public Map<String,Object> findMessage(@RequestParam Map param){
        String errMsg = validate(param);
        int errCode = -1;
        Map<String,Object> result = Maps.newHashMap();
        if(StringUtils.isBlank(errMsg)){

            String aid = (String)param.get("aid");
            String msg = (String)param.get("msg");
            String ua = (String)param.get("ua");
            Reply reply = new Reply();
            reply.setAid(Integer.parseInt(aid));
            reply.setMsg(msg);
            reply.setUa(ua);
            reply.setMid(ctx().getUser().getId());
            reply.setCreateTime(new Date());
            try{
                iReplyService.reply(reply);
                errCode =1;
                errMsg = "回复成功";
                reply.setId(reply.getId());
                result.put("reply",reply);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        result.put("errMsg",errMsg);
        result.put("errCode", errCode);

        return result;
    }

    public String validate(Map<String,String> map){

        if(!ctx().isLogin()) return "未登录阿,别乱来 qwq";

        String aid = map.get("aid");
        String msg = map.get("msg");
        if(StringUtils.isBlank(aid))
            return "非法请求！ :(";
        if(StringUtils.isBlank(msg))
            return "请输入回复内容";
        return "";
    }

    @RequestMapping("rup.json")
    @ResponseBody
    public void up(@RequestParam Integer rid){
        try{
            iReplyService.up(rid);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
