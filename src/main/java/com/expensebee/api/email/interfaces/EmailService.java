package com.expensebee.api.email.interfaces;

import com.expensebee.api.email.entity.EmailMessage;
import jakarta.mail.MessagingException;

public interface EmailService {
  void sendEmail(EmailMessage emailMessage) throws MessagingException;
}
