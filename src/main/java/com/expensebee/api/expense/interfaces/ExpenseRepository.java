package com.expensebee.api.expense.interfaces;

import com.expensebee.api.expense.dto.ExpenseResDTO;
import com.expensebee.api.expense.entity.Expense;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExpenseRepository {
  Optional<Expense> findById(UUID id);
  List<Expense> findAll();
  Expense save(Expense expense);
  Expense update(Expense expense);
  void deleteById(UUID id);
  List<Expense> findAllByUserId(UUID id);
}
