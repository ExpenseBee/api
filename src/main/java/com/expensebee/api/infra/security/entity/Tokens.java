package com.expensebee.api.infra.security.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Tokens {
  private String accessToken;
  private String refreshToken;
}
