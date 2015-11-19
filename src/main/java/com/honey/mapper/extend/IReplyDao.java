package com.honey.mapper.extend;

import com.honey.entity.extend.ReplyVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description 回复
 * @author: chenPeng
 * @date: 2015/9/10 11:17
 * Copyright © 2015/9/10 Shanghai Raxtone Software Co.,Ltd Allright Reserved
 */
public interface IReplyDao {

    /**
     * 获取回复
     * @param aid
     * @return
     */
    List<ReplyVo> findReplysByArticle(@Param("aid") Integer aid);

    /**
     * 喜欢
     * @param rid
     * @return
     */
    int up(@Param("rid") Integer rid);
}
