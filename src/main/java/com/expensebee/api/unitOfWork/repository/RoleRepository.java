package com.expensebee.api.unitOfWork.repository;

import com.expensebee.api.unitOfWork.repository.interfaces.IRoleRepository;
import com.expensebee.api.user.entity.Role;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class RoleRepository {
  private final IRoleRepository IRoleRepository;


  public CompletableFuture<Role> findById(long id) {
    return CompletableFuture.supplyAsync(() ->
      IRoleRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("User with this id not found"))
    );
  }

  public CompletableFuture<Role> save(Role role) {
    return CompletableFuture.supplyAsync(() -> IRoleRepository.save(role));
  }

  public CompletableFuture<Role> update(Role role) {
    return CompletableFuture.supplyAsync(() -> IRoleRepository.save(role));
  }

  public CompletableFuture<Void> delete(Role role) {
    return CompletableFuture.runAsync(() -> IRoleRepository.delete(role));
  }

  public CompletableFuture<List<Role>> findAll() {
    return CompletableFuture.supplyAsync(IRoleRepository::findAll);
  }
}
