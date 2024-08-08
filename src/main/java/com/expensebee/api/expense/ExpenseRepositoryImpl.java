package com.expensebee.api.expense;

import com.expensebee.api.expense.entity.Expense;
import com.expensebee.api.expense.interfaces.ExpenseRepository;
import com.expensebee.api.expense.interfaces.ExpenseRepositoryExt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ExpenseRepositoryImpl implements ExpenseRepository {
  private final ExpenseRepositoryExt expenseRepository;

  @Override
  public Optional<Expense> findById(UUID id) {
    return expenseRepository.findById(id);
  }

  @Override
  public List<Expense> findAll() {
    return expenseRepository.findAll();
  }

  @Override
  public Expense save(Expense expense) {
    return expenseRepository.save(expense);
  }

  @Override
  public Expense update(Expense expense) {
    return this.save(expense);
  }

  @Override
  public void deleteById(UUID id) {
    expenseRepository.deleteById(id);
  }
}
