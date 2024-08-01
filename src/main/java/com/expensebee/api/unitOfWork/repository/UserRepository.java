package com.expensebee.api.unitOfWork.repository;

import com.expensebee.api.unitOfWork.repository.interfaces.IUserRepository;

import com.expensebee.api.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserRepository {
  private final IUserRepository IUserRepository;

  public Optional<User> findById(UUID id) {
    return IUserRepository.findById(id);
  }

  public Optional<User> findByEmail(String email) {
    return IUserRepository.findByEmail(email);
  }

  public Optional<User> findByUserName(String userName) {
    return this.findByEmail(userName);
  }

  public User save(User user) {
    return IUserRepository.save(user);
  }

  public User update(User user) {
    return IUserRepository.save(user);
  }

  public void delete(User user) {
    IUserRepository.delete(user);
  }

  public List<User> findAll() {
    return IUserRepository.findAll();
  }
}
