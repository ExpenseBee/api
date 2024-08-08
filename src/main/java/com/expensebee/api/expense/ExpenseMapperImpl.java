package com.expensebee.api.expense;

import com.expensebee.api.expense.interfaces.ExpenseMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExpenseMapperImpl implements ExpenseMapper {
  private final ModelMapper mapper;
}
