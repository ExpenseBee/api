package com.expensebee.api.auth.interfaces;

import com.expensebee.api.auth.dto.LoginResponseDTO;
import com.expensebee.api.user.entity.User;

public interface AuthMapper {
  LoginResponseDTO toDTO(User user, String token);
}
