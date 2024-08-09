package com.expensebee.api.expense.interfaces;

import com.expensebee.api.expense.dto.ExpenseReqDTO;
import com.expensebee.api.expense.dto.ExpenseResDTO;

import java.util.List;
import java.util.UUID;

public interface ExpenseService {
  ExpenseResDTO create(ExpenseReqDTO expense);
  ExpenseResDTO update(UUID id, ExpenseReqDTO expense);
  void delete(UUID id);
  ExpenseResDTO findById(UUID id);
  List<ExpenseResDTO> findAllByUserId();
}
