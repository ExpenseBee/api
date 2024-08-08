package com.expensebee.api.roles.interfaces;

import com.expensebee.api.roles.entity.Role;

import java.util.List;

public interface RoleRepository {
  Role findById(long id);
  Role save(Role role);
  Role update(Role role);
  void delete(Role role);
  List<Role> findAll();
}
