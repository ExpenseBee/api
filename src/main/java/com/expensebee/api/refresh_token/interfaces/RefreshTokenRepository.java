package com.expensebee.api.refresh_token.interfaces;

import com.expensebee.api.refresh_token.entity.RefreshToken;
import com.expensebee.api.user.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository {
  void save(RefreshToken refreshToken);
  void delete(RefreshToken refreshToken);
  void delete(User user);
  void deleteById(UUID uuid);
  Optional<RefreshToken> findByRefreshTokenId(UUID uuid);
  Optional<RefreshToken> findByUserId(UUID userId);
}
