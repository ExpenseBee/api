package com.expensebee.api.email.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmailMessage {
  private String to;
  private String subject;
  private String body;
}
