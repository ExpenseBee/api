package com.expensebee.api.infra.security;

import com.expensebee.api.infra.security.interfaces.TokenService;
import com.expensebee.api.user.entity.User;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

  @Value("${api.security.token.secret}")
  private String secret;

  @Override
  public String generateToken(User user) {
    var expirationAt = new Date(System.currentTimeMillis() + 1000 * 60 * 5);

    try {
      var algorithm = Jwts.SIG.HS384;
      var key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
      return Jwts.builder()
        .issuer("expenseBee")
        .subject(user.getUsername())
        .expiration(expirationAt)
        .signWith(key, algorithm)
        .compact();
    } catch (JwtException exception) {
      throw new RuntimeException("Error while generating token!", exception);

    }
  }

  @Override
  public String validateToken(String token) {
    try {
      var key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
      return Jwts.parser()
        .decryptWith(key)
        .build()
        .parseEncryptedClaims(token)
        .getPayload()
        .getSubject();

    } catch (JwtException exception) {
      throw new RuntimeException("Error while validating token!", exception);
    }
  }
}
