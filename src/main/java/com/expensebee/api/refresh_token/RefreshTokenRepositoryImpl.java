package com.expensebee.api.refresh_token;

import com.expensebee.api.refresh_token.entity.RefreshToken;
import com.expensebee.api.refresh_token.interfaces.RefreshTokenRepository;
import com.expensebee.api.refresh_token.interfaces.RefreshTokenRepositoryExt;
import com.expensebee.api.user.entity.User;
import com.expensebee.api.user.interfaces.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {
  private final RefreshTokenRepositoryExt refreshTokenRepository;
  private final UserRepository userRepository;

  @Override
  public void save(RefreshToken refreshDTO) {
    refreshTokenRepository.save(refreshDTO);
  }

  @Override
  public void delete(RefreshToken refreshDTO) {
    refreshTokenRepository.delete(refreshDTO);
  }

  @Override
  public void delete(User user) {
    refreshTokenRepository.findByUser(user).ifPresent(refreshTokenRepository::delete);
  }

  @Override
  public void deleteById(UUID uuid) {
    refreshTokenRepository.deleteById(uuid);
  }

  @Override
  public Optional<RefreshToken> findByRefreshTokenId(UUID uuid) {
    return refreshTokenRepository.findById(uuid);
  }

  @Override
  public Optional<RefreshToken> findByUserId(UUID userId) {
    return refreshTokenRepository.findByUserId(userId);
  }
}
