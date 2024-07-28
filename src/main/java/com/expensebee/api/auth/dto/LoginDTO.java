package com.expensebee.api.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

  @Email
  @NotBlank(message = "Username is blank!")
  private String username;

  @NotBlank(message = "Username is blank!")
  private String password;
}
