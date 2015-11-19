package com.honey.web;

import com.honey.HoneyConstants;
import com.honey.entity.auto.Member;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @description 来自吊炸天的思想
 * @author: chenPeng
 * @date: 2015/8/28 10:06
 * Copyright © 2015/8/28 Shanghai Raxtone Software Co.,Ltd Allright Reserved
 */
public class RequestUtil {

    private ServletContext context;
    private HttpSession session;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Map<String, Cookie> cookies;

    private final static int MAX_AGE = 86400 * 31;  // 最大COOKIE有效期 为一个月
    private final static int TWO_WK = 86400 * 14;  // 最大COOKIE有效期 为两星期
    private static final String UTF_8 = "UTF-8";
    private final static ThreadLocal<RequestUtil> contexts = new ThreadLocal<RequestUtil>();

    public ServletContext context() {
        return context;
    }

    public HttpSession session() {
        return session;
    }

    public HttpServletResponse response() {
        return response;
    }

    public Cookie cookie(String name) {
        return cookies.get(name);
    }

    public String header(String name) {
        return request.getHeader(name);
    }

    public void header(String name, String value) {
        response.setHeader(name, value);
    }

    public void header(String name, int value) {
        response.setIntHeader(name, value);
    }

    public void header(String name, long value) {
        response.setDateHeader(name, value);
    }


    /**
     * 初始化请求上下文
     *
     * @param ctx
     * @param req
     * @param res
     */
    public static RequestUtil begin(ServletContext ctx, HttpServletRequest req, HttpServletResponse res) {
        RequestUtil rc = new RequestUtil();
        rc.context = ctx;
        rc.request = req;
        rc.response = res;
        rc.response.setCharacterEncoding(UTF_8);
        rc.session = req.getSession(true);
        rc.cookies = new HashMap<String, Cookie>();
        Cookie[] cookies = req.getCookies();
        if (cookies != null)
            for (Cookie ck : cookies) {
                rc.cookies.put(ck.getName(), ck);
            }
        contexts.set(rc);
        return rc;
    }

    /**
     * 获取当前请求的上下文
     *
     * @return
     */
    public static RequestUtil get() {
        return contexts.get();
    }

    public void end() {
        this.context = null;
        this.request = null;
        this.response = null;
        this.session = null;
        this.cookies = null;
        contexts.remove();
    }


    public void cookie(String name, String value, int max_age) {
        try {
            value = URLEncoder.encode(value, UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Cookie c = new Cookie(name, value); //设定有效时间  以s为单位
        c.setMaxAge(max_age); //设置Cookie路径和域名
        c.setPath("/");
        response.addCookie(c);
    }

    public void cookie(String name, String value) {
        try {
            value = URLEncoder.encode(value, UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Cookie c = new Cookie(name, value); //设定有效时间  以s为单位
        c.setMaxAge(TWO_WK); //设置Cookie路径和域名
        c.setPath("/");
        response.addCookie(c);
    }

    public boolean deleteCookie(String name) {
        if (name != null) {
            Cookie cookie = cookie(name);
            if (cookie != null) {
                cookie.setMaxAge(0);//如果0，就说明立即删除
                cookie.setPath("/");//不要漏掉
                response.addCookie(cookie);
                return true;
            }
        }
        return false;
    }

    public void clearLoginCookieAndSession() {
     /*   deleteCookie(HoneyConstants.LOGIN_COOKIE_ID);
        deleteCookie(HoneyConstants.LOGIN_COOKIE_PWD);*/
        session().removeAttribute(HoneyConstants.LOGIN_SESSION);
    }

    public boolean isLogin() {
        return/* cookie(HoneyConstants.LOGIN_COOKIE_ID) != null
                && cookie(HoneyConstants.LOGIN_COOKIE_PWD) != null
                && */session().getAttribute(HoneyConstants.LOGIN_SESSION) != null;
    }


    public Member getUser() {
        return (Member) session().getAttribute(HoneyConstants.LOGIN_SESSION);
    }

}
