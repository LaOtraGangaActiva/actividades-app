package es.fplumara.dam1.actividades.service.impl;

import es.fplumara.dam1.actividades.service.EmailService;

public class EmailTest {
        public static void main(String[] args) {
            EmailService emailService = new EmailServiceImpl();

            emailService.sendEmail(
                    "ghaforyfarzia@gmail.com",
                    "Testing ",
                    "It works!!!!!! "
            );
        }
    }


