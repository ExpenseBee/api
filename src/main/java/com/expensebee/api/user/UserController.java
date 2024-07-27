package com.expensebee.api.user;

import com.expensebee.api.unitOfWork.UnitOfWork;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
  private final UnitOfWork uow;
}
