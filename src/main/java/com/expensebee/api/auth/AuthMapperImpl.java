package com.expensebee.api.auth;

import com.expensebee.api.auth.dto.LoginResponseDTO;
import com.expensebee.api.auth.interfaces.AuthMapper;
import com.expensebee.api.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthMapperImpl implements AuthMapper {
  private final ModelMapper modelMapper;

  public LoginResponseDTO toDTO(User user, String token) {
    var dto = modelMapper.map(user, LoginResponseDTO.class);
    dto.setToken(token);
    return dto;
  }
}
