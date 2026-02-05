package es.fplumara.dam1.actividades.service;

public interface EmailService {

    void sendEmail(String to, String subject, String body);

}