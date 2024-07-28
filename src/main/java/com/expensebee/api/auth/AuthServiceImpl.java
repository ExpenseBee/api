package com.expensebee.api.auth;

import com.expensebee.api.auth.dto.LoginDTO;
import com.expensebee.api.auth.dto.LoginResponseDTO;
import com.expensebee.api.auth.interfaces.AuthMapper;
import com.expensebee.api.auth.interfaces.AuthService;
import com.expensebee.api.infra.security.interfaces.TokenService;
import com.expensebee.api.unitOfWork.interfaces.UnitOfWork;
import com.expensebee.api.user.dto.CreateUserRequestDTO;
import com.expensebee.api.user.dto.UserResponseDTO;
import com.expensebee.api.user.entity.User;
import com.expensebee.api.user.interfaces.UserMapper;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
  private final UnitOfWork uow;
  private final UserMapper userMapper;
  private final AuthMapper authMapper;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final TokenService tokenService;
  private final AuthenticationManager authenticationManager;

  @Override
  public UserResponseDTO register(CreateUserRequestDTO createUserRequestDTO) {
    var userExist = uow.getUserRepository().findByUserName(createUserRequestDTO.getEmail());
    if (userExist != null) {
      throw new EntityExistsException("User already exists!");
    }

    createUserRequestDTO.setPassword(bCryptPasswordEncoder.encode(createUserRequestDTO.getPassword()));
    var user = uow.getUserRepository().save(userMapper.toModel(createUserRequestDTO));

    return userMapper.toDTO(user);
  }

  @Override
  public LoginResponseDTO login(LoginDTO login) {
    var user = uow.getUserRepository().findByUserName(login.getUsername());

    var usernamePassword = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
    var authenticated = authenticationManager.authenticate(usernamePassword);

    var token = tokenService.generateToken((User) authenticated.getPrincipal());

    return authMapper.toDTO(user, token);
  }

}
