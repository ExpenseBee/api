package com.expensebee.api.expense.entity;

import lombok.Getter;

public enum ExpenseType {
  FOOD("Food"),
  TRANSPORTATION("Transportation"),
  ENTERTAINMENT("Entertainment"),
  HEALTH("Health"),
  EDUCATION("Education"),
  OTHER("Other");

  @Getter
  private final String value;
  private ExpenseType(String value) {
    this.value = value;
  }
}

