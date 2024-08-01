package com.expensebee.api.user;

import com.expensebee.api.unitOfWork.interfaces.UnitOfWork;
import com.expensebee.api.user.entity.User;
import com.expensebee.api.user.interfaces.UserService;
import com.expensebee.api.user.interfaces.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UnitOfWork uow;
  private final UserMapper mapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (username.isBlank()){
      throw new IllegalArgumentException("Username is blank");
    }
    return uow.getUserRepository().findByUserName(username).orElseThrow(() -> new EntityNotFoundException("User with this username not found"));
  }

  public User findByUserName(String userName) {
    return uow.getUserRepository().findByEmail(userName).orElseThrow(() -> new EntityNotFoundException("User with this username not found"));
  }
}
