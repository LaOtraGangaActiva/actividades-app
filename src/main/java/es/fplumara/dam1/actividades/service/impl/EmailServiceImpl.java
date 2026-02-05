package es.fplumara.dam1.actividades.service.impl;

import es.fplumara.dam1.actividades.service.EmailService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailServiceImpl implements EmailService {

    private static final String HOST = "smtp.gmail.com";
    private static final int PORT = 587;

    private final String emailAccount;
    private final String emailPassword;

    public EmailServiceImpl() {
        emailAccount = System.getenv("Email_Account");
        emailPassword = System.getenv("Email_Password");

        if (emailAccount == null || emailPassword == null) {
            throw new IllegalStateException(
                    "Environment variables Email_Account and Email_Password must be defined"
            );
        }
    }

    @Override
    public void sendEmail(String to, String subject, String body) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", String.valueOf(PORT));

        Session session = Session.getInstance(
                props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailAccount, emailPassword);
                    }
                }
        );

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailAccount));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("Error sending email", e);
        }
    }
}


