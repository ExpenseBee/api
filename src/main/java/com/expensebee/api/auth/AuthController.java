package com.expensebee.api.auth;

import com.expensebee.api.auth.dto.LoginDTO;
import com.expensebee.api.auth.dto.LoginResponseDTO;
import com.expensebee.api.user.dto.CreateUserRequestDTO;
import com.expensebee.api.user.dto.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {
  private final AuthServiceImpl authService;

  @PostMapping("create")
  public ResponseEntity<UserResponseDTO> save(@RequestBody CreateUserRequestDTO userRequest){
    return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(userRequest));
  }

  @PostMapping("login")
  public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginRequest){
    return ResponseEntity.status(HttpStatus.OK).body(authService.login(loginRequest));
  }
}
