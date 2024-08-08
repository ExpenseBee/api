package com.expensebee.api.roles.interfaces;

import com.expensebee.api.roles.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface IRoleRepository extends JpaRepository<Role, Long> {
}
