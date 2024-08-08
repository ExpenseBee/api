package com.expensebee.api.expense.interfaces;

import com.expensebee.api.expense.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExpenseRepositoryExt extends JpaRepository<Expense, UUID> {
}
