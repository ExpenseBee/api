package com.expensebee.api.expense.dto;

import com.expensebee.api.expense.entity.ExpenseType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseResDTO {
  private UUID id;
  private ExpenseType type;
  private String title;
  private String description;
  private Double price;
  private Date createdAt;
}
