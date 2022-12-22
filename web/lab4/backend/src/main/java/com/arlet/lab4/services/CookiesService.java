package com.arlet.lab4.services;

import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class CookiesService {

    public Optional<Cookie> getCookieByName(Cookie[] cookies, String name) {
        if (cookies == null)
            return Optional.empty();
        for (Cookie cookie :
                cookies) {
            if (cookie.getName().equals(name))
                return Optional.of(cookie);
        }

        return Optional.empty();
    }

    public Optional<String> getJWTFromCookie(HttpServletRequest request) {
        Optional<Cookie> cookie = getCookieByName(request.getCookies(), "jwt-token");

        if (cookie.isEmpty())
            return Optional.empty();

        return Optional.ofNullable(cookie.get().getValue());
    }
}
