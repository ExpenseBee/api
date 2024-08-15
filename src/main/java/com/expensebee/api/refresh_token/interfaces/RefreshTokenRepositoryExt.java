package com.expensebee.api.refresh_token.interfaces;

import com.expensebee.api.refresh_token.entity.RefreshToken;
import com.expensebee.api.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepositoryExt extends JpaRepository<RefreshToken, UUID> {

  @Query("SELECT r FROM RefreshToken r WHERE r.user = :user")
  Optional<RefreshToken> findByUser(@Param("user") User user);

  @Query("SELECT r FROM RefreshToken r WHERE r.user.id = :userId")
  Optional<RefreshToken> findByUserId(@Param("userId") UUID userId);

  @Modifying
  @Query("DELETE FROM RefreshToken r WHERE r.id = :refreshTokenId")
  void deleteById(@Param("refreshTokenId") UUID refreshTokenId);
}
