package com.expensebee.api.user;

import com.expensebee.api.user.dto.UpdateUserRequestDTO;
import com.expensebee.api.user.dto.UserResponseDTO;
import com.expensebee.api.user.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
@Tag(name = "User", description = "Everything related to the user!")
public class UserController {
  private final UserService userService;

  @Operation(summary = "Current user!")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Current user!"),
    @ApiResponse(responseCode = "404", description = "User not found!"),
    @ApiResponse(responseCode = "500", description = "Something went wrong!"),
  })
  @GetMapping("profile")
  public ResponseEntity<UserResponseDTO> currentUser() {
    return ResponseEntity.status(HttpStatus.OK).body(userService.currentUser());
  }

  @Operation(summary = "Update user!")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "User has been updated successfully!"),
    @ApiResponse(responseCode = "400", description = "UserRequestDTO is null"),
    @ApiResponse(responseCode = "404", description = "User with this username not found"),
  })
  @PutMapping("profile")
  public ResponseEntity<UserResponseDTO> update(@RequestBody() UpdateUserRequestDTO userRequest) {
    return ResponseEntity.status(HttpStatus.OK).body(userService.update(userRequest));
  }
}
