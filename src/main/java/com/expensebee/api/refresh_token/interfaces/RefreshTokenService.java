package com.expensebee.api.refresh_token.interfaces;

import com.expensebee.api.user.entity.User;

import java.util.UUID;

public interface RefreshTokenService {
  void save(String refreshToken);
  void save(String refreshToken, String username);
  void delete(UUID id);
}
