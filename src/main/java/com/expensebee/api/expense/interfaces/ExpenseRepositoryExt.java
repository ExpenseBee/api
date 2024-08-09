package com.expensebee.api.expense.interfaces;

import com.expensebee.api.expense.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

public interface ExpenseRepositoryExt extends JpaRepository<Expense, UUID> {

  @Query("SELECT e FROM Expense e WHERE e.user.id = :id")
  List<Expense> findAllByUserId(@Param("id") UUID id);
}
