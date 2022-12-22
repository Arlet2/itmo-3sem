package com.arlet.lab4.controllers;

import com.arlet.lab4.services.AuthException;
import com.arlet.lab4.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping(value="/api/")
public class AuthController {

    private final AuthService authService;

    public AuthController(@Autowired AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> tryToLogin(@RequestParam String login, @RequestParam String password) {
        try {
            String token = authService.login(login, password);

            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (AuthException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String tryToRegister(@RequestParam String login, @RequestParam String password) {
        try {
            return authService.register(login, password);
        } catch (AuthException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @RequestMapping(value="/token-validation", method = RequestMethod.GET)
    public boolean checkToken(@RequestParam String token) {
        return authService.isJWTValid(token);
    }
}
