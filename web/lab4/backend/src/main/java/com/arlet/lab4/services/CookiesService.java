package com.arlet.lab4.services;

import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.Optional;

@Service
public class CookiesService {

    public Optional<Cookie> getCookieByName(Cookie[] cookies, String name) {
        for (Cookie cookie :
                cookies) {
            if (cookie.getName().equals(name))
                return Optional.of(cookie);
        }

        return Optional.empty();
    }
}
