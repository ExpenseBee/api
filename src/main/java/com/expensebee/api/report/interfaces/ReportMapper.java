package com.expensebee.api.report.interfaces;

import com.expensebee.api.expense.entity.ExpenseType;
import com.expensebee.api.report.dto.ReportExpenseDTO;
import com.expensebee.api.report.dto.ReportResDTO;

import java.util.List;
import java.util.Map;

public interface ReportMapper {
  List<ReportExpenseDTO> toReportExpenseDTO(Map<ExpenseType, Double> expenses);
  ReportResDTO toReportResDTO(List<ReportExpenseDTO> expenseDTOS, Double totalPrice);
}
