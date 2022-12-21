package com.arlet.lab4.filters;

import com.arlet.lab4.services.AuthService;
import com.arlet.lab4.services.CookiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@Component
@WebFilter(urlPatterns = "/api/handler")
@Order(1)
public class AuthFilter implements Filter {

    private final AuthService authService;
    private final CookiesService cookiesService;

    public AuthFilter(@Autowired AuthService authService, @Autowired CookiesService cookiesService) {
        this.authService = authService;
        this.cookiesService = cookiesService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        Optional<String> jwt = cookiesService.getJWTFromCookie((HttpServletRequest) servletRequest);

        if (jwt.isEmpty())
            return;

        if(!authService.isJWTValid(jwt.get()))
            return;

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
