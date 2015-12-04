package com.honey.service.impl;

import com.honey.HoneyConstants;
import com.honey.entity.auto.Member;
import com.honey.mapper.auto.MemberMapper;
import com.honey.mapper.extend.IMemberDao;
import com.honey.service.IMemberService;
import com.honey.util.Md5Util;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description
 * @author: chenPeng
 * @date: 2015/8/27 13:55
 * Copyright © 2015/8/27 Shanghai Raxtone Software Co.,Ltd Allright Reserved
 */

@Service
public class MemberServiceImpl implements IMemberService{

    @Resource
    private IMemberDao iMemberDao;

    @Resource
    private MemberMapper memberMapper;

    /**
     * 登录
     * @param honeyMember
     * @return
     */
    @Override
    public Member userLogin(Member honeyMember) {

        Map<String,Object> param = new HashMap<String,Object>();
        param.put("nikename", honeyMember.getNikename());
        param.put("email", honeyMember.getEmail());
        param.put("password", honeyMember.getPassword());

        Member rsObj = iMemberDao.findByNikenameOrEmail(param);

        if(rsObj!=null){
            String passWordMd5 = rsObj.getPassword();
            if(StringUtils.isNotEmpty(passWordMd5) && StringUtils.isNotEmpty(honeyMember.getPassword())){
                //密码比较
                if(!Md5Util.md5(honeyMember.getPassword()).equals(passWordMd5))
                    return null;
            }
        }
        return rsObj;
    }

    @Override
    public int userRegister(Member member) {
        Date now = new Date();
        member.setPassword(Md5Util.md5(member.getPassword()));
        member.setHeadImg(HoneyConstants.GRAVATAR_URL+ Md5Util.md5(member.getEmail()).toLowerCase());
        member.setCreateTime(now);
        member.setUpdateTime(now);
        memberMapper.insert(member);
        return member.getId();
    }

    @Override
    public boolean userIsExist(Member member) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("nikename", member.getNikename());
        param.put("email", member.getEmail());
        return iMemberDao.findByNikenameOrEmail(param)!=null;
    }

    @Override
    public int updateHeadImg(Integer id, String url) {
        Member member = new Member();
        member.setId(id);
        member.setHeadImg(url);
        return memberMapper.updateByPrimaryKeySelective(member);
    }

}
