package com.example.servlet.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//@WebListener(value = "myServletContextListener")
public class MyServletContextListener implements ServletContextListener {

    private Logger log = LoggerFactory.getLogger(MyServletContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("Start to initialize servlet context...");
        sce.getServletContext().setAttribute("author", "fulgens");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("Destroy servlet context...");
    }
}
