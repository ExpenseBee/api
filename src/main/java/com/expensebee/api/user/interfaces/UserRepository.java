package com.expensebee.api.user.interfaces;

import com.expensebee.api.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
  Optional<User> findById(UUID id);
  Optional<User> findByEmail(String email);
  Optional<User> findByUsername(String username);
  User save(User user);
  User update(User user);
  void delete(User user);
  List<User> findAll();
}
