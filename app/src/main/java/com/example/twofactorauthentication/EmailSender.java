package com.example.twofactorauthentication;


import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

    private final String host = "smtp.gmail.com";
    private final String port = "587";
    private final String userEmail = "senderEmail"; //nje email nga e cila dergohet otp
    private final String userPassword = "password"; //app password per email-in e dhene

    public void sendEmail(String destinationEmail, String otp) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userEmail, userPassword);
            }
        });

        Message message = prepareMessage(session, destinationEmail, otp);
        Transport.send(message);
    }

    private Message prepareMessage(Session session, String destinationEmail , String code) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(userEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(destinationEmail));
        message.setSubject("Your One-Time Password Code");
        message.setText("Your OTP code is " + code);
        return message;
    }
}