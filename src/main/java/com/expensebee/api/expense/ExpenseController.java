package com.expensebee.api.expense;

import com.expensebee.api.expense.interfaces.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("expense")
public class ExpenseController {
  private final ExpenseService expenseService;
}
