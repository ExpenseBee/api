package com.expensebee.api.auth;

import com.expensebee.api.auth.dto.LoginResponseDTO;
import com.expensebee.api.auth.interfaces.AuthMapper;
import com.expensebee.api.infra.security.entity.Tokens;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthMapperImpl implements AuthMapper {
  private final ModelMapper modelMapper;

  @Override
  public LoginResponseDTO toDTO(Tokens tokens) {
    return modelMapper.map(tokens, LoginResponseDTO.class);
  }
}
