package com.expensebee.api.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequestDTO {
  @NotBlank(message = "First is blank!")
  private String firstName;

  @NotBlank(message = "Last is blank!")
  private String lastName;
}
