package com.expensebee.api.unitOfWork;

import com.expensebee.api.unitOfWork.interfaces.UnitOfWork;
import com.expensebee.api.unitOfWork.repository.RoleRepository;
import com.expensebee.api.unitOfWork.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UnitOfWorkImpl implements UnitOfWork {

  private final EntityManager entityManager;
  private EntityTransaction transaction;

  @Getter
  private final UserRepository userRepository;

  @Getter
  private final RoleRepository roleRepository;


  @Override
  @Transactional
  public void beginTransaction() {
    transaction = entityManager.getTransaction();
    transaction.begin();
  }

  @Override
  @Transactional
  public void commit() {
    if (transaction != null) {
      transaction.commit();
    }
  }

  @Override
  @Transactional
  public void rollback() {
    if (transaction != null) {
      transaction.rollback();
    }
  }

  @Override
  @Transactional
  public void close() {
    if (transaction != null && transaction.isActive()) {
      transaction.rollback();
    }
  }
}
