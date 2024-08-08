package com.expensebee.api.expense;

import com.expensebee.api.expense.interfaces.ExpenseRepository;
import com.expensebee.api.expense.interfaces.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
  private ExpenseRepository expenseRepository;
}
