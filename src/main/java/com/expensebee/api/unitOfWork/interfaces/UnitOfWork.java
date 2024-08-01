package com.expensebee.api.unitOfWork.interfaces;

public interface UnitOfWork extends AutoCloseable, UnitUserRepository, UnitRoleRepository {
  void beginTransaction();
  void commit();
  void rollback();
  void close();
}
