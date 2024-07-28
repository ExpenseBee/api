package com.expensebee.api.infra.security.interfaces;

import com.expensebee.api.user.entity.User;

public interface TokenService {
  String generateToken(User user);
  String validateToken(String token);
}
