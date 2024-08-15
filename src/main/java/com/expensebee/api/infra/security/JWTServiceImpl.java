package com.expensebee.api.infra.security;

import com.expensebee.api.infra.security.entity.ExpirationTime;
import com.expensebee.api.infra.security.entity.Tokens;
import com.expensebee.api.infra.security.interfaces.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JWTServiceImpl implements JWTService {
  private final JwtEncoder jwtEncoder;

  @Override
  public String generate(Authentication authentication, Optional<Long> expirationTime) {
    var instant = Instant.now();
    var exp = expirationTime.orElse(ExpirationTime.FIVE_MINUTES.getValue());

    var scope = authentication
      .getAuthorities().stream()
      .map(GrantedAuthority::getAuthority)
      .collect( Collectors.joining(" "));

    var claims = JwtClaimsSet.builder()
      .issuer("expensebee")
      .issuedAt(instant)
      .expiresAt(instant.plusSeconds(exp))
      .subject(authentication.getName())
      .claim("scope", scope)
      .build();

    return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
  }

  @Override
  public Tokens generateTokens(Authentication authentication) {
    var accessToken = this.generate(authentication, Optional.empty());
    var refreshToken = this.generate(authentication, Optional.of(ExpirationTime.FIVE_HOURS.getValue()));
    return new Tokens(accessToken, refreshToken);
  }
}
