package com.fivesysdev.Fiveogram.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt_secret}")
    private String secret;

    public String generateToken(String email) {
//        Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(60).toInstant());
        Date expirationDate = Date.from(ZonedDateTime.now().plusDays(1).toInstant());

        return JWT.create()
                .withSubject("User details")
                .withClaim("username", email)
                .withIssuedAt(new Date())
                .withIssuer("Five-o-gram")
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(secret));
    }
    public String validate(String token){
        String jwt = token.substring(7);
        return validateTokenAndRetrieveClaim(jwt);
    }

    public String validateTokenAndRetrieveClaim(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User details")
                .withIssuer("Five-o-gram")
                .build();

        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("username").asString();
    }
}