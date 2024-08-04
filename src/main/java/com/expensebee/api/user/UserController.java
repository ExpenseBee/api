package com.expensebee.api.user;

import com.expensebee.api.user.dto.UserResponseDTO;
import com.expensebee.api.user.interfaces.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
@Tag(name = "User", description = "Everything related to the user!")
public class UserController {
  private final UserService userService;

  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Current user!"),
    @ApiResponse(responseCode = "404", description = "User not found!"),
    @ApiResponse(responseCode = "500", description = "Something went wrong!")
  })
  @GetMapping("profile")
  public ResponseEntity<UserResponseDTO> profile() {
    return ResponseEntity.status(HttpStatus.OK).body(userService.currentUser());
  }
}
