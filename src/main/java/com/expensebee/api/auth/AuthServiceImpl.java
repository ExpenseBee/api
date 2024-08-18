package com.expensebee.api.auth;

import com.expensebee.api.auth.dto.LoginDTO;
import com.expensebee.api.auth.dto.LoginResponseDTO;
import com.expensebee.api.auth.interfaces.AuthMapper;
import com.expensebee.api.auth.interfaces.AuthService;
import com.expensebee.api.infra.security.entity.Tokens;
import com.expensebee.api.infra.security.interfaces.JWTService;
import com.expensebee.api.refresh_token.interfaces.RefreshTokenService;
import com.expensebee.api.user.dto.CreateUserRequestDTO;
import com.expensebee.api.user.dto.UserResponseDTO;
import com.expensebee.api.user.interfaces.UserRepository;
import com.expensebee.api.user.interfaces.UserMapper;
import com.expensebee.api.user.interfaces.UserService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final AuthMapper authMapper;
  private final JWTService jwtService;
  private final UserService userService;
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

    var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));

    var jwt = jwtService.generateTokens(authentication);
    refreshTokenService.save(jwt.getRefreshToken(), user.getUsername());

    return authMapper.toDTO(jwt);
  }

  @Override
  public Tokens newTokens(String refreshToken) {
    var refreshTokenFound = refreshTokenService.findRefreshToken(refreshToken);
    refreshTokenService.delete(refreshTokenFound.getId());

    var username = userService.getJwtToken().getClaimAsString("sub");
    var user = userService.findByUserName(username);

    var authentication = new UsernamePasswordAuthenticationToken(
      user, null, user.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authentication);
    var jwt = jwtService.generateTokens(authentication);
    refreshTokenService.save(jwt.getRefreshToken(), user.getUsername());

    return jwt;
  }

  @Override
  public void logout(HttpServletRequest request, HttpServletResponse response) {
    var authentication = SecurityContextHolder
      .getContext()
      .getAuthentication();

    var username = userService.getJwtToken().getClaimAsString("sub");
    var refreshToken = userService.findByUserName(username).getRefreshToken();
    var refreshTokenFound = refreshTokenService.findRefreshToken(refreshToken.getRefreshToken());
    refreshTokenService.delete(refreshTokenFound.getId());

    new SecurityContextLogoutHandler()
      .logout(request, response, authentication);
    SecurityContextHolder
      .clearContext();
  }
}
