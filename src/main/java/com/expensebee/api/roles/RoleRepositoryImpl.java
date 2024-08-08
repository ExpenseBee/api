package com.expensebee.api.roles;

import com.expensebee.api.roles.interfaces.IRoleRepository;
import com.expensebee.api.roles.entity.Role;
import com.expensebee.api.roles.interfaces.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {
  private final IRoleRepository IRoleRepository;


  public Role findById(long id) {
    return IRoleRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("User with this id not found"));
  }

  public Role save(Role role) {
    return IRoleRepository.save(role);
  }

  public Role update(Role role) {
    return IRoleRepository.save(role);
  }

  public void delete(Role role) {
    IRoleRepository.delete(role);
  }

  public List<Role> findAll() {
    return IRoleRepository.findAll();
  }
}
