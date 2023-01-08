package com.arlet.lab4.controllers;

import com.arlet.lab4.services.AuthException;
import com.arlet.lab4.services.AuthService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping(value = "/api/")
public class AuthController {

    private final AuthService authService;

    public AuthController(@Autowired AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> tryToLogin(@RequestBody UserBody userBody) {
        try {
            String token = authService.login(userBody.login, userBody.password);

            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (AuthException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String tryToRegister(@RequestBody UserBody userBody) {
        try {
            return authService.register(userBody.login, userBody.password);
        } catch (AuthException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @RequestMapping(value = "/token-validation", method = RequestMethod.GET)
    public boolean checkToken(@RequestParam String token) {
        return authService.isJWTValid(token);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    private static class UserBody {
        private String login;
        private String password;
    }
}
