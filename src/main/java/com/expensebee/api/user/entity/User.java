package com.expensebee.api.user.entity;

import com.expensebee.api.expense.entity.Expense;
import com.expensebee.api.refresh_token.entity.RefreshToken;
import com.expensebee.api.roles.entity.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_user")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "user_id")
  private UUID id;

  private String email;

  private String password;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;


  @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinTable(
    name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private List<Role> roles;

  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  private List<Expense> expenses;

  @OneToOne(mappedBy = "user", fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  private RefreshToken refreshToken;

  @CreationTimestamp()
  @Column(name = "created_at")
  private Date createdAt;

  @UpdateTimestamp()
  @Column(name = "updated_at")
  private Date updatedAt;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "account_expired_at")
  private Date accountExpiredAt;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "credentials_expired_at")
  private Date credentialsExpiredAt;

  @Column(name = "account_non_locked", nullable = false)
  private boolean accountNonLocked;

  @Column(name = "credential_non_expired", nullable = false)
  private boolean credentialsNonExpired;

  @Column(nullable = false)
  private boolean enabled;

  @PrePersist
  private void prePersist(){
    this.accountNonLocked = true;
    this.credentialsNonExpired = true;
    this.enabled = true;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Set<GrantedAuthority> authorities = new HashSet<>();
    for (Role role : roles) {
      authorities.add(new SimpleGrantedAuthority(role.getName().name()));
    }

    return authorities;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return accountExpiredAt == null || accountExpiredAt.after(new Date());
  }

  @Override
  public boolean isAccountNonLocked() {
    return accountNonLocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return credentialsExpiredAt == null || credentialsExpiredAt.after(new Date());
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }
}
