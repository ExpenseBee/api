package com.expensebee.api.auth.interfaces;

import com.expensebee.api.auth.dto.LoginDTO;
import com.expensebee.api.auth.dto.LoginResponseDTO;
import com.expensebee.api.infra.security.entity.Tokens;
import com.expensebee.api.user.dto.CreateUserRequestDTO;
import com.expensebee.api.user.dto.UserResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
  UserResponseDTO register(CreateUserRequestDTO createUserRequestDTO);
  LoginResponseDTO login(LoginDTO login);
  Tokens newTokens(String refreshToken);
  void logout(HttpServletRequest request, HttpServletResponse response);
}
