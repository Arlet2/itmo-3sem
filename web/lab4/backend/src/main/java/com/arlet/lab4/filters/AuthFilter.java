package com.arlet.lab4.filters;

import com.arlet.lab4.services.AuthService;
import com.arlet.lab4.services.CookiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class AuthFilter extends GenericFilterBean {

    private final AuthService authService;
    private final CookiesService cookiesService;

    public AuthFilter(@Autowired AuthService authService, @Autowired CookiesService cookiesService) {
        this.authService = authService;
        this.cookiesService = cookiesService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        System.out.println("AUTH FILTER CALLED");

        var request = (HttpServletRequest) servletRequest;
        var response = (HttpServletResponse) servletResponse;

        Optional<String> jwt = cookiesService.getJWTFromCookie(request);

        if (jwt.isEmpty()) {
            System.out.println("No jwt request");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        if(!authService.isJWTValid(jwt.get())) {
            System.out.println("Request with invalid jwt");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
