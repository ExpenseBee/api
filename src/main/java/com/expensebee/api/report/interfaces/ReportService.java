package com.expensebee.api.report.interfaces;

import com.expensebee.api.expense.dto.ExpenseResDTO;
import com.expensebee.api.report.dto.ReportResDTO;

import java.util.Date;
import java.util.List;

public interface ReportService {
  List<ExpenseResDTO> rangeDate(Date start, Date end);
  ReportResDTO monthlyReport();
}
