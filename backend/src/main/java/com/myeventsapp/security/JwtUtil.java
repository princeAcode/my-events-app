package com.myeventsapp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
  private final Key key;
  private final long accessTtlMillis;
  private final long refreshTtlMillis;

  public JwtUtil(
      @Value("${app.jwt.secret}") String secret,
      @Value("${app.jwt.accessTtlMin}") long accessTtlMin,
      @Value("${app.jwt.refreshTtlDays}") long refreshTtlDays) {
    this.key = Keys.hmacShaKeyFor(secret.getBytes());
    this.accessTtlMillis = accessTtlMin * 60 * 1000;
    this.refreshTtlMillis = refreshTtlDays * 24 * 60 * 60 * 1000;
  }

  public String generateAccessToken(String subject, Map<String, Object> claims) {
    return generateToken(subject, claims, accessTtlMillis);
  }

  public String generateRefreshToken(String subject, Map<String, Object> claims) {
    return generateToken(subject, claims, refreshTtlMillis);
  }

  private String generateToken(String subject, Map<String, Object> claims, long ttlMillis) {
    Instant now = Instant.now();
    return Jwts.builder()
        .setSubject(subject)
        .addClaims(claims)
        .setIssuedAt(Date.from(now))
        .setExpiration(Date.from(now.plusMillis(ttlMillis)))
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();
  }

  public Claims parse(String token) {
    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
  }
}
