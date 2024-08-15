package com.expensebee.api.infra.security.interfaces;

import com.expensebee.api.infra.security.entity.Tokens;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface JWTService {
  String generate(Authentication authentication, Optional<Long> expirationTime);
  Tokens generateTokens(Authentication authentication);
}
