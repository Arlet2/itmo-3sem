package com.arlet.lab4.services;

import com.arlet.lab4.data.User;
import com.arlet.lab4.repositories.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordHashCreator passwordHashCreator;

    @Value("${jwt_secret_key}")
    private String secretKey;

    private final Algorithm algorithm;

    public AuthService(@Autowired UserRepository userRepository, @Autowired PasswordHashCreator passwordHashCreator) {
        this.userRepository = userRepository;
        this.passwordHashCreator = passwordHashCreator;
        algorithm = Algorithm.HMAC256(secretKey);
    }

    public String login(String login, String password) {
        if(!userRepository.existsUserByLogin(login))
            throw new AuthException("Логина не существует");

        User currentUser = userRepository.getUserByLogin(login);

        if (!currentUser.getPassword().equals(
                passwordHashCreator.createHash(password, currentUser.getSalt()))
        )
            throw new AuthException("Пароль неверен");

        return createJWT(login);
    }

    public String register(String login, String password) {
        if (userRepository.existsUserByLogin(login))
            throw new AuthException("Такое имя пользователя уже существует");

        if (password.length() < 6)
            throw new AuthException("Пароль должен состоять минимум из 6 символов");

        return createJWT(login);
    }

    private String createJWT(String login) {
        return JWT.create()
                .withClaim("login", login)
                .sign(algorithm);
    }

    public boolean isJWTValid(String jwt) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();

            verifier.verify(jwt);
        } catch (JWTVerificationException e) {
            return false;
        }

        return true;
    }
}
