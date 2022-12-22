package com.arlet.lab4.filters;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class APIFilter extends GenericFilterBean {

    private final String[] allowedOrigins;

    public APIFilter(@Value("${allowed_api_origins}") String[] allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        var request = (HttpServletRequest) servletRequest;
        var response = (HttpServletResponse) servletResponse;

        System.out.println("API FILTER CALLED");

        for (String origin: allowedOrigins) {
            if (request.getHeader("Origin").equals(origin)) {
                response.setHeader("Access-Control-Allow-Origin", origin);
                break;
            }
        }

        if (response.getHeader("Access-Control-Allow-Origin") == null ||
                response.getHeader("Access-Control-Allow-Origin").isEmpty()) {
            System.out.println("CORS blocking...");
            return;
        }

        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
