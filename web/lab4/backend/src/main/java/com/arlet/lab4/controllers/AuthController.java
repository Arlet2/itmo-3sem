package com.arlet.lab4.controllers;

import com.arlet.lab4.services.AuthException;
import com.arlet.lab4.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping(value="/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(@Autowired AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String tryToLogin(@RequestParam String login, @RequestParam String password) {

        try {
            return authService.login(login, password);
        } catch (AuthException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String tryToRegister(@RequestParam String login, @RequestParam String password) {
        try {
            return authService.register(login, password);
        } catch (AuthException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
