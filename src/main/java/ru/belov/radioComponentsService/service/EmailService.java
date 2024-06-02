package ru.belov.radioComponentsService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.belov.radioComponentsService.jwt.Token;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }


    public void confirmEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("bev6889@yandex.ru");
        message.setTo(email);
        String strToken= Token.getJwt(email);
        message.setSubject("регистрация");
        message.setText("localhost:8080/token/" + strToken);
        emailSender.send(message);
    }

    public void recoverPasswordEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("bev6889@yandex.ru");
        message.setTo(email);
        String strToken=Token.getJwt(email);
        message.setSubject("восстановление пароля");
        message.setText("localhost:8080/recovery/"+strToken);
        emailSender.send(message);
    }


}
