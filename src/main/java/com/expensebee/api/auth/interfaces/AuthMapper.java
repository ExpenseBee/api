package com.expensebee.api.auth.interfaces;

import com.expensebee.api.auth.dto.LoginResponseDTO;
import com.expensebee.api.infra.security.entity.Tokens;

public interface AuthMapper {
  LoginResponseDTO toDTO(Tokens tokens);
}
