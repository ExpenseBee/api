package com.expensebee.api.user;

import com.expensebee.api.email.interfaces.EmailService;
import com.expensebee.api.user.dto.ChargePasswordRequestDTO;
import com.expensebee.api.user.dto.UpdateUserRequestDTO;
import com.expensebee.api.user.dto.UserResponseDTO;
import com.expensebee.api.user.entity.User;
import com.expensebee.api.user.interfaces.UserRepository;
import com.expensebee.api.user.interfaces.UserService;
import com.expensebee.api.user.interfaces.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;
  private final EmailService emailService;

  private Jwt getJwtToken() {
    var authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication instanceof JwtAuthenticationToken) {
      return (Jwt) authentication.getPrincipal();
    }else {
      throw new RuntimeException("No valid authentication token");
    }
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (username.isBlank()){
      throw new IllegalArgumentException("Username is blank");
    }
    return userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User with this username not found"));
  }

  public User findByUserName(String userName) {
    return userRepository.findByEmail(userName).orElseThrow(() -> new EntityNotFoundException("User with this username not found"));
  }

  @Override
  public UserResponseDTO currentUser() {
    var username = getJwtToken().getClaimAsString("sub");
    var user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User with this username not found"));

    return userMapper.toDTO(user);
  }

  @Override
  public UserResponseDTO update(UpdateUserRequestDTO userRequestDTO) {
    if (userRequestDTO == null) {
      throw new IllegalArgumentException("UserRequestDTO is null");
    }

    var usernameInJwt = getJwtToken().getClaimAsString("sub");
    var userFound = userRepository.findByUsername(usernameInJwt).orElseThrow(() -> new EntityNotFoundException("User with this username not found"));

    var userModel = userMapper.toModel(userRequestDTO, userFound);

    var userUpdated = userRepository.save(userModel);

    return userMapper.toDTO(userUpdated);
  }

  @Override
  public String chargePassword(ChargePasswordRequestDTO chargePasswordRequestDTO) {
    var username = getJwtToken().getClaimAsString("sub");
    var user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User with this username not found"));

    chargePasswordRequestDTO.setPassword(passwordEncoder.encode(chargePasswordRequestDTO.getPassword()));
    var userChargedPassword = userMapper.toModel(chargePasswordRequestDTO, user);

    userRepository.save(userChargedPassword);

    return "Your password has been charged";
  }

  @Override
  public void delete() {
    var user = userRepository.findByUsername(getJwtToken().getClaimAsString("sub")).orElseThrow(() -> new EntityNotFoundException("User with this username not found"));
    userRepository.delete(user);
  }
}
