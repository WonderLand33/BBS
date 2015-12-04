package com.honey.ctrl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.honey.HoneyConstants;
import com.honey.entity.auto.Member;
import com.honey.service.IMemberService;
import com.honey.util.ValidateUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description
 * @author: chenPeng
 * @date: 2015/8/25 16:20
 * Copyright © 2015/8/25 Shanghai Raxtone Software Co.,Ltd Allright Reserved
 */

@Controller
@RequestMapping("/")
public class UserCtrl extends BaseCtrl {

    private static final Logger log = LoggerFactory.getLogger(UserCtrl.class);

    @Resource
    private IMemberService iMemberService;

    @RequestMapping("/")
    public String toIndex() {
        return "index";
    }

    @RequestMapping("/loginInfo.json")
    @ResponseBody
    public Member loginInfo() {
        Member member = null;
        if (ctx().isLogin()) {
            log.info("已登录");
            member = (Member) ctx().session().getAttribute(HoneyConstants.LOGIN_SESSION);
        }
        return member;
    }


    @RequestMapping("login.json")
    @ResponseBody
    public Map userLogin(@RequestParam() Map loginParams) {
        String nikename = null;
        String email = null;
        String id = (String) loginParams.get("id");
        String pwd = (String) loginParams.get("password");
        log.info("login param is {}", loginParams);
        Map<String, Object> rsMap = new HashMap<String, Object>();
        String msg = " T.T 登录失败，用户名或密码错误！";
        int code = -1;
        long timeSp = new Date().getTime();
        if (StringUtils.isEmpty(id) ||
                StringUtils.isEmpty(pwd)) {
            msg = "咦,你是怎么发现这里的？好孩子不要干坏事哟~";
        } else {
            if (id.indexOf("@") > 0) {//如果是邮箱登录
                email = id;
            } else { //如果是昵称登录
                nikename = id;
            }
            Member honeyMember = new Member();
            honeyMember.setNikename(nikename);
            honeyMember.setEmail(email);
            honeyMember.setPassword(pwd);
            Member rsObj = iMemberService.userLogin(honeyMember);
            if (rsObj != null) {
                //ctx().cookie(HoneyConstants.LOGIN_COOKIE_ID, id);//设置cookie两个星期
                // ctx().cookie(HoneyConstants.LOGIN_COOKIE_PWD, pwd);
                ctx().session().setAttribute(HoneyConstants.LOGIN_SESSION, rsObj);//两个月过期
                rsObj.setPassword("default");//涂抹源对象密码
                code = 1;
                msg = "登录成功鸟~ 0.0";
                rsMap.put("user", rsObj);
            }
        }
        rsMap.put("code", code);
        rsMap.put("msg", msg);
        rsMap.put("timeSp", timeSp);
        return rsMap;
    }


    @RequestMapping("loginOut.json")
    @ResponseBody
    public void loginOut() {
        ctx().clearLoginCookieAndSession(); //删除登录信息
    }


    @RequestMapping("register.json")
    @ResponseBody
    public Map<String, Object> register(@RequestParam Map param) {
        String errMsg = validate(param);

        log.info("register error:{}", errMsg);
        int errCode = -1;
        Map<String, Object> result = Maps.newHashMap();
        if (StringUtils.isBlank(errMsg)) {

           /* String name = (String)param.get("name");
            String email = (String)param.get("email");
            String password = (String)param.get("password");
            Member member = new Member();*/

            JSONObject jsonObject = new JSONObject(param);
            Member member = JSON.toJavaObject(jsonObject, Member.class);

            if (iMemberService.userIsExist(member)) {
                errMsg = "账号已存在！";
            } else {
                try {
                    iMemberService.userRegister(member);
                    errCode = 1;
                    ctx().session().setAttribute(HoneyConstants.LOGIN_SESSION, member);//两个月过期
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        result.put("errMsg", errMsg);
        result.put("errCode", errCode);

        return result;
    }

    public String validate(Map<String, String> map) {


        String name = map.get("nikename");
        String email = map.get("email");
        String password = map.get("password");

        if (StringUtils.isBlank(name))
            return "请输入昵称";
        if (!ValidateUtil.checkUserName(name))
            return "用户名格式错误";
        if (StringUtils.isBlank(email))
            return "请输入邮箱";
        if (!ValidateUtil.isEmail(email))
            return "邮箱无效";
        if (StringUtils.isBlank(password))
            return "请输入密码";
        if (!ValidateUtil.checkPwd(password))
            return "密码格式错误(6-16位)";
        return "";
    }


    @RequestMapping("setHeadImg.json")
    @ResponseBody
    public void setHeadImg(@RequestParam String url) {

        log.info("head image url is {}", url);
        try {
            iMemberService.updateHeadImg(ctx().getUser().getId(), url);
            ctx().getUser().setHeadImg(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
