package com.expensebee.api.unitOfWork.repository.interfaces;

import com.expensebee.api.unitOfWork.repository.interfaces.genericInterface.RepositoryGeneric;
import com.expensebee.api.user.entity.Role;
import org.springframework.stereotype.Component;

@Component
public interface IRoleRepository extends RepositoryGeneric<Role, Long> {
}
