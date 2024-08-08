package com.expensebee.api.roles.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "tb_roles")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "role_id")
  private long id;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, unique = true)
  private Values name;

  @CreationTimestamp()
  private Date createdAt;

  @UpdateTimestamp()
  private Date updatedAt;

  public enum Values {
    USER(1L),
    ADMIN(2L),
    DEV(3L);

    final long id;
    Values(long id) {
      this.id = id;
    }
    public long getId() {
      return id;
    }
  }
}
