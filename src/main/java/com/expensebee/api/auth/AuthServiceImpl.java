package com.expensebee.api.auth;

import com.expensebee.api.auth.dto.LoginDTO;
import com.expensebee.api.auth.dto.LoginResponseDTO;
import com.expensebee.api.auth.interfaces.AuthMapper;
import com.expensebee.api.auth.interfaces.AuthService;
import com.expensebee.api.infra.security.interfaces.JWTService;
import com.expensebee.api.refresh_token.interfaces.RefreshTokenService;
import com.expensebee.api.user.dto.CreateUserRequestDTO;
import com.expensebee.api.user.dto.UserResponseDTO;
import com.expensebee.api.user.interfaces.UserRepository;
import com.expensebee.api.user.interfaces.UserMapper;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final AuthMapper authMapper;
  private final JWTService jwtService;
  private final PasswordEncoder bCryptPasswordEncoder;
  private final RefreshTokenService refreshTokenService;
  private final AuthenticationManager authenticationManager;

  @Override
  public UserResponseDTO register(CreateUserRequestDTO createUserRequestDTO) {
    var userExist = userRepository.findByUsername(createUserRequestDTO.getEmail());
    if (userExist.isPresent()) {
      throw new EntityExistsException("User already exists!");
    }

    createUserRequestDTO.setPassword(bCryptPasswordEncoder.encode(createUserRequestDTO.getPassword()));
    var user = userRepository.save(userMapper.toModel(createUserRequestDTO));

    return userMapper.toDTO(user);
  }

  @Override
  public LoginResponseDTO login(LoginDTO login) {
    var user = userRepository.findByUsername(login.getUsername()).orElseThrow(EntityNotFoundException::new);
    refreshTokenService.delete(user.getRefreshToken().getId());

    var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));

    var jwt = jwtService.generateTokens(authentication);
    refreshTokenService.save(jwt.getRefreshToken(), user.getUsername());

    return authMapper.toDTO(jwt);
  }
}
