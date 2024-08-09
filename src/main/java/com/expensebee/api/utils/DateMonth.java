package com.expensebee.api.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;

public class DateMonth {

  public static Date firstDay() {
    var currentDate = localeDateNow();

    var firstDayOfMonth = currentDate.withDayOfMonth(1);

    return converterDate(firstDayOfMonth);
  }

  public static Date lastDay() {
    var currentDate = localeDateNow();

    var yearMonth = YearMonth.from(currentDate);
    var lastDayOfMonth = yearMonth.atEndOfMonth();

    return converterDate(lastDayOfMonth);
  }

  private static LocalDate localeDateNow() {
    return Instant.now().atZone(ZoneId.systemDefault()).toLocalDate();
  }

  private static Date converterDate(LocalDate localDate) {
    return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
  }
}
