package com.honey.ctrl;

import com.honey.entity.extend.WyyVo;
import com.honey.util.HttpClientUtils;
import com.jayway.jsonpath.JsonPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @description
 * @author: chenPeng
 * @date: 2015/9/14 14:16
 * Copyright © 2015/9/14 Shanghai Raxtone Software Co.,Ltd Allright Reserved
 */

@Controller
@RequestMapping("w")
public class WyyCtrl {

    private static final Logger log = LoggerFactory.getLogger(UserCtrl.class);


    public WyyVo httpReader(String songId) {
        String url = "http://music.163.com/api/song/detail?id={id}&ids=[{ids}]";
        url = url.replace("{id}", songId).replace("{ids}", songId);
        WyyVo wyyVo = null;
        HttpClientUtils http = HttpClientUtils.getInstance();
        try {
            String remoteResult = http.httpGet(url);
            log.info("解析网易云{} 返回数据：{}", url, remoteResult);
            wyyVo = new WyyVo();
            wyyVo.setName(String.valueOf(JsonPath.read(remoteResult, "$.songs[0].name")));
            wyyVo.setUrl(String.valueOf(JsonPath.read(remoteResult, "$.songs[0].mp3Url")));
            wyyVo.setSinger(String.valueOf(JsonPath.read(remoteResult, "$.songs[0].artists[0].name")));
            wyyVo.setId(JsonPath.<Integer>read(remoteResult, "$.songs[0].id"));
        } catch (IOException e) {
            e.printStackTrace();
            log.error("解析网易云音乐时发生错误!");

        }

        return wyyVo;
    }


    @RequestMapping("wyy.json")
    @ResponseBody
    public WyyVo wyy(@RequestParam String id){
        return this.httpReader(id);
    }
}
