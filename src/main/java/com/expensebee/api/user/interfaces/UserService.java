package com.expensebee.api.user.interfaces;

import com.expensebee.api.user.dto.ChargePasswordRequestDTO;
import com.expensebee.api.user.dto.UpdateUserRequestDTO;
import com.expensebee.api.user.dto.UserResponseDTO;
import com.expensebee.api.user.entity.User;
import jakarta.mail.MessagingException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.Jwt;

public interface UserService extends UserDetailsService {
  User findByUserName(String userName);
  UserResponseDTO currentUser();
  UserResponseDTO update(UpdateUserRequestDTO userRequestDTO);
  String chargePassword(ChargePasswordRequestDTO chargePasswordRequestDTO);
  void delete();
  Jwt getJwtToken();
}
