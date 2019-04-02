package com.denghb.simplex.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean authFilter() {
        AuthAccessFilter aa = new AuthAccessFilter();

        FilterRegistrationBean reg = new FilterRegistrationBean();
        reg.setFilter(aa);
        reg.addUrlPatterns("/*");
        return reg;
    }
}
