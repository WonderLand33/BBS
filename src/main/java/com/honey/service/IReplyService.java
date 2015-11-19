package com.honey.service;

import com.honey.entity.auto.Reply;
import com.honey.entity.extend.ReplyVo;

import java.util.List;

/**
 * @description
 * @author: chenPeng
 * @date: 2015/9/10 14:40
 * Copyright © 2015/9/10 Shanghai Raxtone Software Co.,Ltd Allright Reserved
 */
public interface IReplyService {

    List<ReplyVo> findReplysByArticle(Integer aid);

    int reply(Reply reply);

    int up(Integer aid);

}
