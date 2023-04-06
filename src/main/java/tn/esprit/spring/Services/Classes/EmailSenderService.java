package tn.esprit.spring.Services.Classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import tn.esprit.spring.DAO.Entities.Email;
import java.io.File;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service

public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public String sendSimpleEmail(Email details) {
        SimpleMailMessage message = new SimpleMailMessage();

        try {

            message.setFrom("growfundspi@gmail.com");
            message.setTo(details.getRecipient());
            message.setText(details.getMsgBody());
            message.setSubject(details.getSubject());

            mailSender.send(message);
            return "Mail Sent Successfully...";
        }
        catch (Exception e) {
            return "Error while Sending Mail";
        }


    }








    // Method 2
    // To send an email with attachment

    }








