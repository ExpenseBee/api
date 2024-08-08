package com.expensebee.api.expense;

import com.expensebee.api.expense.interfaces.ExpenseRepository;
import com.expensebee.api.expense.interfaces.ExpenseRepositoryExt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ExpenseRepositoryImpl implements ExpenseRepository {
  private final ExpenseRepositoryExt expenseRepository;
}
