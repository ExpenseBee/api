package com.expensebee.api.auth.dto;

import com.expensebee.api.user.dto.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {
  UserResponseDTO user;
  String token;
}
