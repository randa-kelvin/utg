package com.untucapital.usuite.utg.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<MobileNumberFilter> mobileNumberFilter() {
        FilterRegistrationBean<MobileNumberFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MobileNumberFilter());
        registrationBean.addUrlPatterns("/check_mobile");
        return registrationBean;
    }

//    @Bean
//    public FilterRegistrationBean<LoggingFilter> loggingFilter() {
//        FilterRegistrationBean<LoggingFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new LoggingFilter());
//        registrationBean.addUrlPatterns("/*");
//        return registrationBean;
//    }
}
