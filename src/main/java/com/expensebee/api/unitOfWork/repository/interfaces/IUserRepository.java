package com.expensebee.api.unitOfWork.repository.interfaces;

import com.expensebee.api.unitOfWork.repository.interfaces.genericInterface.RepositoryGeneric;
import com.expensebee.api.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public interface IUserRepository extends RepositoryGeneric<User, UUID> {
  Optional<User> findByEmail(String username);
}
