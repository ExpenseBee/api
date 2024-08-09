package com.expensebee.api.report;

import com.expensebee.api.expense.entity.ExpenseType;
import com.expensebee.api.report.dto.ReportExpenseDTO;
import com.expensebee.api.report.dto.ReportResDTO;
import com.expensebee.api.report.interfaces.ReportMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReportMapperImpl implements ReportMapper {
  private final ModelMapper modelMapper;

  @Override
  public List<ReportExpenseDTO> toReportExpenseDTO(Map<ExpenseType, Double> expenses) {
    return expenses.entrySet().stream()
      .map(entry -> new ReportExpenseDTO(entry.getKey().name(), entry.getValue()))
      .collect(Collectors.toList());
  }

  @Override
  public ReportResDTO toReportResDTO(List<ReportExpenseDTO> expenseDTOS, Double totalPrice) {
    var dto = new ReportResDTO();
    dto.setExpenses(expenseDTOS);
    dto.setTotalAmount(totalPrice);

    return dto;
  }
}
