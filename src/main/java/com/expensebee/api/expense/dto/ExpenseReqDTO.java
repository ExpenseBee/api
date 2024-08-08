package com.expensebee.api.expense.dto;

import com.expensebee.api.expense.entity.ExpenseType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseReqDTO {
  @NotNull
  private ExpenseType type;
  @NotNull
  private String title;
  @Null
  private String description;
  @NotNull
  private Double price;
}
