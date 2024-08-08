package com.expensebee.api.expense;

import com.expensebee.api.expense.dto.ExpenseReqDTO;
import com.expensebee.api.expense.dto.ExpenseResDTO;
import com.expensebee.api.expense.interfaces.ExpenseMapper;
import com.expensebee.api.expense.interfaces.ExpenseRepository;
import com.expensebee.api.expense.interfaces.ExpenseService;
import com.expensebee.api.user.interfaces.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
  private final ExpenseRepository expenseRepository;
  private final ExpenseMapper expenseMapper;
  private final UserService userService;

  @Override
  public ExpenseResDTO create(ExpenseReqDTO expenseReqDTO) {
    var expense = expenseMapper.toModel(expenseReqDTO);

    var username = userService.getJwtToken().getClaimAsString("sub");
    var user = userService.findByUserName(username);
    expense.setUser(user);

    var expenseSaved = expenseRepository.save(expense);
    return expenseMapper.ToDTO(expenseSaved);
  }

  @Override
  public ExpenseResDTO update(UUID id, ExpenseReqDTO expenseReqDTO) {
    var expense = expenseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Expense not found"));
    expense = expenseMapper.toModel(expenseReqDTO, expense);
    expense = expenseRepository.save(expense);

    return expenseMapper.ToDTO(expense);
  }

  @Override
  public void delete(UUID id) {
    expenseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Expense not found"));
    expenseRepository.deleteById(id);
  }

  @Override
  public ExpenseResDTO findById(UUID id) {
    var expense = expenseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Expense not found"));
    return expenseMapper.ToDTO(expense);
  }

  @Override
  public List<ExpenseResDTO> findAll() {
    var expenses = expenseRepository.findAll();
    return expenseMapper.ToDTO(expenses);
  }
}
