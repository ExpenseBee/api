package com.expensebee.api.refresh_token.interfaces;

import com.expensebee.api.refresh_token.entity.RefreshToken;

import java.util.UUID;

public interface RefreshTokenService {
  void save(String refreshToken);
  void save(String refreshToken, String username);
  void delete(UUID id);
  RefreshToken findRefreshToken(String refreshToken);
}
