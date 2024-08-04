package com.expensebee.api.user.interfaces;

import com.expensebee.api.user.dto.UserResponseDTO;
import com.expensebee.api.user.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
  User findByUserName(String userName);
  UserResponseDTO currentUser();
}
