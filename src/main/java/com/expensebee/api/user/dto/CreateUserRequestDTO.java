package com.expensebee.api.user.dto;

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
public class CreateUserRequestDTO {
  @Email
  @NotBlank(message = "Email is blank!")
  private String email;

  @NotBlank(message = "Password is blank!")
  private String password;

  @NotBlank(message = "First name is blank!")
  private String firstName;

  @NotBlank(message = "Last name is blank!")
  private String lastName;
}
