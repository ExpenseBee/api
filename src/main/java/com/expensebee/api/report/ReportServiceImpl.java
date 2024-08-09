package com.expensebee.api.report;

import com.expensebee.api.expense.dto.ExpenseResDTO;
import com.expensebee.api.expense.entity.ExpenseType;
import com.expensebee.api.expense.interfaces.ExpenseService;
import com.expensebee.api.report.dto.ReportExpenseDTO;
import com.expensebee.api.report.dto.ReportResDTO;
import com.expensebee.api.report.interfaces.ReportMapper;
import com.expensebee.api.report.interfaces.ReportService;
import com.expensebee.api.utils.DateMonth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
  private final ExpenseService expenseService;
  private final ReportMapper reportMapper;

  @Override
  public List<ExpenseResDTO> rangeDate(Date start, Date end) {
    var expenses = expenseService.findAllByUserId();

    var startDate = instantZone(start);
    var endDate = instantZone(end);

    return expenses.stream().filter(expense -> {
        var createdAt = expense.getCreatedAt().toInstant()
          .atZone(ZoneId.systemDefault())
          .toLocalDate();
        return !createdAt.isBefore(startDate) && !createdAt.isAfter(endDate);
      })
      .collect(Collectors.toList());
  }

  @Override
  public ReportResDTO monthlyReport() {
    var firstDay = DateMonth.firstDay();
    var lastDay = DateMonth.lastDay();

    var expenses = this.rangeDate(firstDay, lastDay);

    var allExpensesByCategory = expenses.stream()
      .collect(Collectors.groupingBy(
        ExpenseResDTO::getType,
        Collectors.summingDouble(ExpenseResDTO::getPrice)
      ));

    var expensesByCategory = reportMapper.toReportExpenseDTO(allExpensesByCategory);

    var totalPrice = expensesByCategory.stream().mapToDouble(ReportExpenseDTO::getAmount)
      .sum();

    return reportMapper.toReportResDTO(expensesByCategory, totalPrice);
  }

  private LocalDate instantZone(Date date) {
    return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
  }
}
