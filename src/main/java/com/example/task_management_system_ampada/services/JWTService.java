package com.example.task_management_system_ampada.services;

import com.example.task_management_system_ampada.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    final static String SIGN_IN_KEY = "586E3272357538782F413F4428472B4B6250655367566B597033733676397924";

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();

    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getClaims(token);
        return claimsResolver.apply(claims);
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SIGN_IN_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(
            User userDetails,
            Map<String, Object> extraClaims
    ) {
        return Jwts.builder().setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(User user) {
        return generateToken(user, new  HashMap<>());
    }

    public boolean isValidToken(User user, String token) {
        String username = extractUsername(token);

        return (username.equals(user.getUsername()) && !isTokenExpired(token));

    }

    private boolean isTokenExpired(String token) {
        return extractExpirationDate(token).before(new Date());

    }

    private Date extractExpirationDate(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

}
