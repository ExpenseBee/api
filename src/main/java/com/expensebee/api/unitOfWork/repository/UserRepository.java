package com.expensebee.api.unitOfWork.repository;

import com.expensebee.api.unitOfWork.repository.interfaces.IUserRepository;

import com.expensebee.api.user.entity.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class UserRepository {
  private final IUserRepository IUserRepository;

  public CompletableFuture<User> findById(UUID id) {
    return CompletableFuture.supplyAsync(() ->
      IUserRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("User with this id not found"))
    );
  }

  public CompletableFuture<User> findByEmail(String email) {
    return CompletableFuture.supplyAsync(() ->
      IUserRepository.findByEmail(email)
        .orElseThrow(() -> new EntityNotFoundException("User with this email not found"))
    );
  }

  public CompletableFuture<User> findByUserName(String userName) {
    return this.findByEmail(userName);
  }

  public CompletableFuture<User> save(User user) {
    return CompletableFuture.supplyAsync(() -> IUserRepository.save(user));
  }

  public CompletableFuture<User> update(User user) {
    return CompletableFuture.supplyAsync(() -> IUserRepository.save(user));
  }

  public CompletableFuture<Void> delete(User user) {
    return CompletableFuture.runAsync(() -> IUserRepository.delete(user));
  }

  public CompletableFuture<List<User>> findAll() {
    return CompletableFuture.supplyAsync(IUserRepository::findAll);
  }
}
