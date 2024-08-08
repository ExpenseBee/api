package com.expensebee.api.user.interfaces;

import com.expensebee.api.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryExt extends JpaRepository<User, UUID> {
  Optional<User> findByEmail(String username);
}
