package com.arlet.lab4.controllers;

import com.arlet.lab4.data.Point;
import com.arlet.lab4.repositories.PointsRepository;
import com.arlet.lab4.services.AuthService;
import com.arlet.lab4.services.CookiesService;
import com.arlet.lab4.services.HitCheckService;
import com.arlet.lab4.services.ValidationService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="api/handler")
public class PointsController {

    private final CookiesService cookiesService;
    private final AuthService authService;
    private final ValidationService validationService;
    private final HitCheckService hitCheckService;
    private final PointsRepository pointsRepository;
    public PointsController(@Autowired CookiesService cookiesService,
                            @Autowired AuthService authService,
                            @Autowired ValidationService validationService,
                            @Autowired HitCheckService hitCheckService,
                            @Autowired PointsRepository pointsRepository) {
        this.cookiesService = cookiesService;
        this.authService = authService;
        this.validationService = validationService;
        this.hitCheckService = hitCheckService;
        this.pointsRepository = pointsRepository;
    }

    @RequestMapping(value="/add-point", consumes = "application/json", method = RequestMethod.POST)
    public void addPoint(@RequestBody PointBody pointBody, HttpServletRequest request) {

        Optional<String> jwt = getJWTFromCookie(request);

        if (jwt.isEmpty())
            return;

        long startTime = System.nanoTime();
        Point point = new Point();

        if(validationService.validateInputData(pointBody.x, pointBody.y, pointBody.r))
            point.setStatus(
                    hitCheckService.checkCoordinates(pointBody.x, pointBody.y, pointBody.r)?
                    "correct":"incorrect");
        else
            point.setStatus("error");

        point.setX(pointBody.x);
        point.setY(pointBody.y);
        point.setR(pointBody.r);

        point.setDate(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss - VV O")));

        long finalTime = System.nanoTime()-startTime;

        point.setScriptTime(new DecimalFormat("#0.00").format(finalTime*Math.pow(10, -6))+" мс");

        point.setOwner(authService.decodeJWT(jwt.get()).getClaim("login").asString());

        pointsRepository.save(point);
    }

    @RequestMapping(value = "/points", method = RequestMethod.GET)
    public List<Point> getPoints(HttpServletRequest request) {

        Optional<String> jwt = getJWTFromCookie(request);

        if (jwt.isEmpty())
            return null;

        if(!authService.isJWTValid(jwt.get()))
            return null;

        return pointsRepository.findAllByOwner(authService.decodeJWT(jwt.get()).getClaim("login").asString());
    }

    private Optional<String> getJWTFromCookie(HttpServletRequest request) {
        Optional<Cookie> cookie = cookiesService.getCookieByName(request.getCookies(), "jwt-token");

        if (cookie.isEmpty())
            return Optional.empty();

        return Optional.ofNullable(cookie.get().getValue());
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    private static class PointBody {
        private int x;
        private float y;
        private int r;
    }
}
