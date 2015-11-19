package com.honey.service;

import com.honey.entity.auto.Member;

/**
 * @description
 * @author: chenPeng
 * @date: 2015/8/27 13:52
 * Copyright © 2015/8/27 Shanghai Raxtone Software Co.,Ltd Allright Reserved
 */
public interface IMemberService {

    Member userLogin(Member member);

    int userRegister(Member member);

    boolean userIsExist(Member member);

    int updateHeadImg(Integer id,String url);
}
