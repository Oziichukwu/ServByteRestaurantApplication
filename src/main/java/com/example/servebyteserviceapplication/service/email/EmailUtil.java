package com.example.servebyteserviceapplication.service.email;

public interface EmailUtil {

    void sendEmail(String toAddress, String subject, String body);
}
