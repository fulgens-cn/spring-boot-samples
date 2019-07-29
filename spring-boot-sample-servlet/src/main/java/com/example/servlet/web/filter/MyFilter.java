package com.example.servlet.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter(filterName = "myFilter", urlPatterns = {"/myServlet"})
public class MyFilter implements Filter {

    private Logger log = LoggerFactory.getLogger(MyFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("MyFilter init...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("MyFilter doFilter...");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        log.info("MyFilter destroy...");
    }
}
