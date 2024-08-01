package com.expensebee.api.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
  private UUID id;
  private String email;
  private String firstName;
  private String lastName;
}
