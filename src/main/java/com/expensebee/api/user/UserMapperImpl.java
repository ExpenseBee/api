package com.expensebee.api.user;

import com.expensebee.api.user.dto.ChargePasswordRequestDTO;
import com.expensebee.api.user.dto.CreateUserRequestDTO;
import com.expensebee.api.user.dto.UpdateUserRequestDTO;
import com.expensebee.api.user.dto.UserResponseDTO;
import com.expensebee.api.user.entity.User;
import com.expensebee.api.user.interfaces.UserMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {
  private final ModelMapper modelMapper;

  public User toModel(CreateUserRequestDTO user) {
    return modelMapper.map(user, User.class);
  }
  public User toModel(UpdateUserRequestDTO userRequestDTO, User user) {
    modelMapper.map(userRequestDTO, user);
    return user;
  }
  public UserResponseDTO toDTO(User user) {
    return modelMapper.map(user, UserResponseDTO.class);
  }
  public List<UserResponseDTO> toDTO(List<User> users) {
    return users.stream().map(this::toDTO).toList();
  }

  @Override
  public User toModel(ChargePasswordRequestDTO passwordRequestDTO, User user) {
    modelMapper.map(passwordRequestDTO, user);
    return user;
  }
}
