package com.expensebee.api.email;

import com.expensebee.api.email.entity.EmailMessage;
import com.expensebee.api.email.interfaces.EmailService;
import jakarta.mail.*;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
  private final JavaMailSender mailSender;

  public void sendEmail(EmailMessage emailMessage) throws MessagingException {
    var message = new SimpleMailMessage();

    message.setFrom("noreply@expensebee.com");
    message.setTo(emailMessage.getTo());
    message.setSubject(emailMessage.getSubject());
    message.setText(emailMessage.getBody());

    mailSender.send(message);
  }
}
