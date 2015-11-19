package com.honey.ctrl;

import com.alibaba.fastjson.JSON;
import com.honey.HoneyConstants;
import com.honey.entity.auto.Member;
import com.honey.web.RequestUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description
 * @author: chenPeng
 * @date: 2015/8/28 9:57
 * Copyright © 2015/8/28 Shanghai Raxtone Software Co.,Ltd Allright Reserved
 */
public class BaseCtrl {

    public String redirect(String url) {
        return "redirect:" + url;
    }

    public String forward(String url){
        return "forward:" + url;
    }

    public RequestUtil ctx() {
        return RequestUtil.get();
    }



}
