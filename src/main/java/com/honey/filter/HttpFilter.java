package com.honey.filter;


import com.alibaba.fastjson.JSON;
import com.honey.HoneyConstants;
import com.honey.entity.auto.Member;
import com.honey.web.RequestUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description
 * @author: chenPeng
 * @date: 2015/8/28 10:31
 * Copyright © 2015/8/28 Shanghai Raxtone Software Co.,Ltd Allright Reserved
 */
public class HttpFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        req.setAttribute("ctx", req.getContextPath()); //设置公共属性

        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        try {
            RequestUtil.begin(null, req, resp);
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            if (RequestUtil.get() != null) {
                RequestUtil.get().end();
            }

        }
    }

    @Override
    public void destroy() {

    }
}
