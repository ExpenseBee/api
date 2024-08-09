package com.expensebee.api.report.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReportResDTO {
  private List<ReportExpenseDTO> expenses;
  private Double totalAmount;
}
