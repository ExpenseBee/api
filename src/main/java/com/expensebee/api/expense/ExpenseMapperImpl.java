package com.expensebee.api.expense;

import com.expensebee.api.expense.dto.ExpenseReqDTO;
import com.expensebee.api.expense.dto.ExpenseResDTO;
import com.expensebee.api.expense.entity.Expense;
import com.expensebee.api.expense.interfaces.ExpenseMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ExpenseMapperImpl implements ExpenseMapper {
  private final ModelMapper mapper;

  @Override
  public Expense toModel(ExpenseReqDTO expenseReqDTO) {
    return mapper.map(expenseReqDTO, Expense.class);
  }

  @Override
  public ExpenseResDTO ToDTO(Expense expense) {
    return mapper.map(expense, ExpenseResDTO.class);
  }

  @Override
  public List<ExpenseResDTO> ToDTO(List<Expense> expenseList) {
    return expenseList.stream().map(expense -> mapper.map(expense, ExpenseResDTO.class)).toList();
  }
}
