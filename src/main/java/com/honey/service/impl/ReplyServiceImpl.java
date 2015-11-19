package com.honey.service.impl;

import com.honey.entity.auto.Reply;
import com.honey.entity.extend.ReplyVo;
import com.honey.mapper.auto.ReplyMapper;
import com.honey.mapper.extend.IReplyDao;
import com.honey.service.IReplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description
 * @author: chenPeng
 * @date: 2015/9/10 14:42
 * Copyright © 2015/9/10 Shanghai Raxtone Software Co.,Ltd Allright Reserved
 */
@Service
public class ReplyServiceImpl implements IReplyService{

    @Resource
    private IReplyDao iReplyDao;

    @Resource
    private ReplyMapper replyMapper;


    @Override
    public List<ReplyVo> findReplysByArticle(Integer aid) {
        return iReplyDao.findReplysByArticle(aid);
    }

    @Override
    public int reply(Reply reply) {
        return replyMapper.insertSelective(reply);
    }

    @Override
    public int up(Integer aid) {
        return iReplyDao.up(aid);
    }
}
