package com.expensebee.api.user.interfaces;

import com.expensebee.api.user.dto.ChargePasswordRequestDTO;
import com.expensebee.api.user.dto.CreateUserRequestDTO;
import com.expensebee.api.user.dto.UpdateUserRequestDTO;
import com.expensebee.api.user.dto.UserResponseDTO;
import com.expensebee.api.user.entity.User;

import java.util.List;

public interface UserMapper {
  User toModel(CreateUserRequestDTO user);
  User toModel(UpdateUserRequestDTO userRequestDTO, User user);
  UserResponseDTO toDTO(User user);
  List<UserResponseDTO> toDTO(List<User> users);
  User toModel(ChargePasswordRequestDTO passwordRequestDTO, User user);
}
