package com.expensebee.api.auth.interfaces;

import com.expensebee.api.auth.dto.LoginDTO;
import com.expensebee.api.auth.dto.LoginResponseDTO;
import com.expensebee.api.user.dto.CreateUserRequestDTO;
import com.expensebee.api.user.dto.UserResponseDTO;

public interface AuthService {
  UserResponseDTO register(CreateUserRequestDTO createUserRequestDTO);
  LoginResponseDTO login(LoginDTO login);
}
