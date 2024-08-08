package com.expensebee.api.expense.interfaces;

import com.expensebee.api.expense.dto.ExpenseReqDTO;
import com.expensebee.api.expense.dto.ExpenseResDTO;
import com.expensebee.api.expense.entity.Expense;

import java.util.List;

public interface ExpenseMapper {
  Expense toModel(ExpenseReqDTO expenseReqDTO);
  ExpenseResDTO ToDTO(Expense expense);
  List<ExpenseResDTO> ToDTO(List<Expense> expenseList);
  Expense toModel(ExpenseReqDTO expenseReqDTO, Expense expense);
}
