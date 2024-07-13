package com.example.timeTracker.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt_secret}")
    private String secretWord;

    @Value("${jwt_expired}")
    private long timeOfExpirationInMinutes;

    private JWTVerifier verifier;
    @PostConstruct
    private void initVerifier() {
         verifier = JWT.require(Algorithm.HMAC256(secretWord)).
                withSubject("Person details").
                withIssuer("authService").
                build();
    }


    public String generateToken(String login){
        Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(timeOfExpirationInMinutes).toInstant());
        return JWT.create().withSubject("Person details").
                withClaim("login",login).
                withIssuer("authService").
                withIssuedAt(new Date()).
                withExpiresAt(expirationDate).
                sign(Algorithm.HMAC256(secretWord));
    }
    public String validateTokenAndRetrieveClaimLogin(String token) throws JWTVerificationException {
        try {
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim("login").asString();
        } catch (JWTVerificationException | IllegalArgumentException e) {
            throw new JWTVerificationException("JWT token is invalid or expired");
        }
    }

    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken !=null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }
}
