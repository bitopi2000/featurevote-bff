package com.featurevote.bff.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class JwtUtil {

    // Use a Base64-encoded secret that is at least 256 bits for HS256.
    private static final String SECRET = "replace-this-with-a-long-secure-secret-key-at-least-32-chars";
    private static final ZoneId ZONE_ID = ZoneId.systemDefault();

    private final SecretKey signingKey = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public String generateToken(String username) {
        LocalDate issuedDate = LocalDate.now();
        LocalDate expirationDate = issuedDate.plusDays(1);

        return Jwts.builder()
                .subject(username)
                .issuedAt(toDate(issuedDate))
                .expiration(toDate(expirationDate))
                .signWith(signingKey)
                .compact();
    }

    public String extractUsername(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    private Date toDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZONE_ID).toInstant());
    }
}
