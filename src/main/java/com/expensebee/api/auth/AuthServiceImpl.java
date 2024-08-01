package com.expensebee.api.auth;

import com.expensebee.api.auth.dto.LoginDTO;
import com.expensebee.api.auth.dto.LoginResponseDTO;
import com.expensebee.api.auth.interfaces.AuthMapper;
import com.expensebee.api.auth.interfaces.AuthService;
import com.expensebee.api.infra.security.interfaces.JWTService;
import com.expensebee.api.unitOfWork.interfaces.UnitOfWork;
import com.expensebee.api.user.dto.CreateUserRequestDTO;
import com.expensebee.api.user.dto.UserResponseDTO;
import com.expensebee.api.user.interfaces.UserMapper;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
  private final UnitOfWork uow;
  private final UserMapper userMapper;
  private final AuthMapper authMapper;
  private final JWTService jwtService;
  private final AuthenticationManager authenticationManager;
  private final PasswordEncoder bCryptPasswordEncoder;

  @Override
  public UserResponseDTO register(CreateUserRequestDTO createUserRequestDTO) {
    var userExist = uow.getUserRepository().findByUserName(createUserRequestDTO.getEmail());
    if (userExist.isPresent()) {
      throw new EntityExistsException("User already exists!");
    }

    createUserRequestDTO.setPassword(bCryptPasswordEncoder.encode(createUserRequestDTO.getPassword()));
    var user = uow.getUserRepository().save(userMapper.toModel(createUserRequestDTO));

    return userMapper.toDTO(user);
  }

  @Override
  public LoginResponseDTO login(LoginDTO login) {
    var user = uow.getUserRepository().findByUserName(login.getUsername()).orElseThrow(EntityNotFoundException::new);

    var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));

    var jwt = jwtService.generate(authentication);
    return authMapper.toDTO(user, jwt);
  }

}
