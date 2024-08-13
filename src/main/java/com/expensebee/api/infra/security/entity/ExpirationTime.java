package com.expensebee.api.infra.security.entity;

import lombok.Getter;

public enum ExpirationTime {
  FIVE_MINUTES(300L),
  FIVE_HOURS(18000L);

  @Getter
  private final long value;
  private ExpirationTime(long value) {
    this.value = value;
  }
}
