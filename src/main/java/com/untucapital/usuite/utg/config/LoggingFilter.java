package com.untucapital.usuite.utg.config;

import com.untucapital.usuite.utg.utils.FormatterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author: Chirinda Nyasha Dell 22/11/2021
 */

@Component
public class LoggingFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Initializing Logging Filter: {}", FormatterUtil.toJson(this));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        RequestWrapper requestWrapper = new RequestWrapper(req);
        log.info("> Usuite UTG Request > - {}: {}, parameters={}, body={}", req.getMethod(), req.getRequestURI(), FormatterUtil.toJson(req.getParameterMap()), requestWrapper.getBody());

        chain.doFilter(requestWrapper, response);

        log.info("< Usuite UTG Response < - {}: {}", res.getStatus(), res.getHeaderNames().toArray());
    }

    @Override
    public void destroy() {
        log.warn("Destructing Logging Filter: {}", this);
    }
}
