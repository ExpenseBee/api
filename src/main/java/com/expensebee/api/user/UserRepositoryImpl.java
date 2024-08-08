package com.expensebee.api.user;

import com.expensebee.api.user.entity.User;
import com.expensebee.api.user.interfaces.UserRepository;
import com.expensebee.api.user.interfaces.UserRepositoryExt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
  private final UserRepositoryExt userRepository;

  public Optional<User> findById(UUID id) {
    return userRepository.findById(id);
  }

  public Optional<User> findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public Optional<User> findByUsername(String userName) {
    return this.findByEmail(userName);
  }

  public User save(User user) {
    return userRepository.save(user);
  }

  public User update(User user) {
    return userRepository.save(user);
  }

  public void delete(User user) {
    userRepository.delete(user);
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }
}
