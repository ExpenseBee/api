package com.expensebee.api.expense.entity;

import com.expensebee.api.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id")
  private UUID id;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ExpenseType type;

  @Column(nullable = false)
  private String title;

  private String description;

  private Double price;

  @CreationTimestamp()
  @Column(name = "created_at")
  private Date createdAt;

  @UpdateTimestamp()
  @Column(name = "updated_at")
  private Date updatedAt;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
}
