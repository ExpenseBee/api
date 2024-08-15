package com.expensebee.api.auth;

import com.expensebee.api.auth.dto.LoginDTO;
import com.expensebee.api.auth.dto.LoginResponseDTO;
import com.expensebee.api.infra.security.entity.Tokens;
import com.expensebee.api.user.dto.CreateUserRequestDTO;
import com.expensebee.api.user.dto.UserResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  @Operation(summary = "New tokens!")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "New tokens created successful!"),
    @ApiResponse(responseCode = "404", description = "User not exist!"),
    @ApiResponse(responseCode = "404", description = "Refresh Token not exist!")
  })
  @GetMapping("new-tokens/{refreshToken}")
  public ResponseEntity<Tokens> newTokens(@PathVariable String refreshToken) {
    return ResponseEntity.status(HttpStatus.OK).body(authService.newTokens(refreshToken));
  }
}
