package com.untucapital.usuite.utg.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

@Configuration
public class OSIVConfig {

    @Bean
    public FilterRegistrationBean<OpenEntityManagerInViewFilter> osivFilter() {
        FilterRegistrationBean<OpenEntityManagerInViewFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new OpenEntityManagerInViewFilter());
        registration.addUrlPatterns("/*"); // You can specify the URL patterns to which this filter applies
        return registration;
    }
}
