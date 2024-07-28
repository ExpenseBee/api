package com.expensebee.api.unitOfWork.interfaces;

import com.expensebee.api.unitOfWork.repository.RoleRepository;
import com.expensebee.api.unitOfWork.repository.UserRepository;

public interface UnitUserRepository {
  UserRepository getUserRepository();
}
