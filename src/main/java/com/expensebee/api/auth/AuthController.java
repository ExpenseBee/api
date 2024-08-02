package com.expensebee.api.auth;

import com.expensebee.api.auth.dto.LoginDTO;
import com.expensebee.api.auth.dto.LoginResponseDTO;
import com.expensebee.api.user.dto.CreateUserRequestDTO;
import com.expensebee.api.user.dto.UserResponseDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Auth", description = "Where user creation and authentication will occur!")
public class AuthController {
  private final AuthServiceImpl authService;

  @Operation(summary = "Create new user!")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "User created successfully!"),
    @ApiResponse(responseCode = "409", description = "User already exists!"),
  })
  @PostMapping("create")
  public ResponseEntity<UserResponseDTO> save(@RequestBody CreateUserRequestDTO userRequest){
    return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(userRequest));
  }

  @Operation(summary = "Log in the user!")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Login successful!"),
    @ApiResponse(responseCode = "404", description = "User not exist!")
  })
  @PostMapping("login")
  public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginRequest){
    return ResponseEntity.status(HttpStatus.OK).body(authService.login(loginRequest));
  }
}
