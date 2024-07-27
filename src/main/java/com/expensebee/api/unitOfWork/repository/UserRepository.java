package com.expensebee.api.unitOfWork.repository;

import com.expensebee.api.unitOfWork.repository.interfaces.IUserRepository;

import com.expensebee.api.user.entity.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserRepository {
  private final IUserRepository IUserRepository;

  public User findById(UUID id) {
    return IUserRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("User with this id not found"));
  }

  public User findByEmail(String email) {
    return IUserRepository.findByEmail(email)
        .orElseThrow(() -> new EntityNotFoundException("User with this email not found"));
  }

  public User findByUserName(String userName) {
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
