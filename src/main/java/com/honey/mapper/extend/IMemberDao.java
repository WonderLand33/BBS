package com.honey.mapper.extend;

import com.honey.entity.auto.Member;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @description
 * @author: chenPeng
 * @date: 2015/8/25 17:19
 * Copyright © 2015/8/25 Shanghai Raxtone Software Co.,Ltd Allright Reserved
 */
public interface IMemberDao {


    /**
     * 根据昵称或邮箱查找用户
     * @param param
     * @return
     */
    Member findByNikenameOrEmail(@Param("map")Map param);
}
