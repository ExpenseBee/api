package com.expensebee.api.refresh_token;

import com.expensebee.api.refresh_token.entity.RefreshToken;
import com.expensebee.api.refresh_token.interfaces.RefreshTokenRepository;
import com.expensebee.api.refresh_token.interfaces.RefreshTokenService;
import com.expensebee.api.user.interfaces.UserRepository;
import com.expensebee.api.user.interfaces.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
  private final UserService userService;
  private final UserRepository userRepository;
  private final RefreshTokenRepository refreshTokenRepository;

  @Override
  public void save(String refreshToken) {
    var username = userService.getJwtToken().getClaimAsString("sub");
    var user = userRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);

    refreshTokenRepository.save(new RefreshToken(null, refreshToken, user, null));
  }

  @Override
  public void save(String refreshToken, String username) {
    var user = userRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);

    refreshTokenRepository.save(new RefreshToken(null, refreshToken, user, null));
  }

  @Override
  public void delete(UUID refreshTokenId) {
    var refreshTokenFound = refreshTokenRepository
      .findByRefreshTokenId(refreshTokenId)
      .orElseThrow(()->
        new EntityNotFoundException("Token not found")
      );

    refreshTokenRepository.deleteById(refreshTokenFound.getId());
  }

  @Override
  public RefreshToken findRefreshToken(String refreshToken) {
    return refreshTokenRepository
      .findByRefreshToken(refreshToken)
      .orElseThrow(() ->
        new EntityNotFoundException("Token not found")
      );
  }
}
