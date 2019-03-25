package com.xue.demo.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
  
  @Autowired
  private JavaMailSender mailSender;

  //发件人设置
  @Value("${spring.mail.username}")
  private String from;

  /**
   * 邮件发送
   * @param subject   主题
   * @param content   内容
   * @param toEmail   收件人
   */
  public void sendSimpleMail(String subject,String content,String toEmail){
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(from);
    message.setTo(toEmail);
    message.setSubject(subject);
    message.setText(content);
    mailSender.send(message);
  }
  
}
