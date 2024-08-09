package com.expensebee.api.report.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReportExpenseDTO {
  private String category;
  private Double amount;
}
