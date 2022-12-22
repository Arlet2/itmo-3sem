package com.arlet.lab4.configurations;

import com.arlet.lab4.filters.APIFilter;
import com.arlet.lab4.filters.SecureFilter;
import com.arlet.lab4.services.AuthService;
import com.arlet.lab4.services.CookiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FiltersConfiguration {
    @Bean
    public FilterRegistrationBean<SecureFilter> createSecureFilter(
            @Autowired AuthService authService, @Autowired CookiesService cookiesService) {
        var filter = new FilterRegistrationBean<SecureFilter>();

        filter.setFilter(new SecureFilter(authService, cookiesService));

        filter.addUrlPatterns("/api/secure/*");

        return filter;
    }

    @Bean
    public FilterRegistrationBean<APIFilter> createFilterAPIFilter() {
        var filter = new FilterRegistrationBean<APIFilter>();

        filter.setFilter(new APIFilter());

        filter.addUrlPatterns("/api/*");

        filter.setOrder(0);

        return filter;
    }
}
