package com.honey.filter;

import com.honey.HoneyConstants;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @description
 * @author: chenPeng
 * @date: 2015/8/17 9:24
 * Copyright © 2015/8/17 Shanghai Raxtone Software Co.,Ltd Allright Reserved
 */
public class ContextListener implements ServletContextListener  {

    private final String ctx = "_host";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute(ctx, HoneyConstants.WEB_DOMAIN);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().removeAttribute(ctx);
    }
}
