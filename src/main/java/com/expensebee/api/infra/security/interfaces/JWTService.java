package com.expensebee.api.infra.security.interfaces;

import org.springframework.security.core.Authentication;

public interface JWTService {
  String generate(Authentication authentication);
}
