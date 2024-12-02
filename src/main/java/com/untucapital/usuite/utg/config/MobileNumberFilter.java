package com.untucapital.usuite.utg.config;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class MobileNumberFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(httpServletRequest) {
            @Override
            public String getParameter(String name) {
                String value = super.getParameter(name);
                if ("mobile".equals(name) && value != null) {
                    value = value.replaceFirst("^0+(?!$)", "");
                }
                return value;
            }

            @Override
            public Map<String, String[]> getParameterMap() {
                return super.getParameterMap().entrySet().stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> {
                                    if ("mobile".equals(entry.getKey())) {
                                        String[] values = entry.getValue();
                                        for (int i = 0; i < values.length; i++) {
                                            values[i] = values[i].replaceFirst("^0+(?!$)", "");
                                        }
                                        return values;
                                    }
                                    return entry.getValue();
                                }
                        ));
            }

            @Override
            public String[] getParameterValues(String name) {
                String[] values = super.getParameterValues(name);
                if ("mobile".equals(name) && values != null) {
                    for (int i = 0; i < values.length; i++) {
                        values[i] = values[i].replaceFirst("^0+(?!$)", "");
                    }
                }
                return values;
            }
        };

        chain.doFilter(requestWrapper, response);
    }

    @Override
    public void destroy() {
    }
}
