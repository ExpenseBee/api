package com.expensebee.api.unitOfWork.repository.interfaces.genericInterface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface RepositoryGeneric<T, ID> extends JpaRepository<T, ID> {
}
