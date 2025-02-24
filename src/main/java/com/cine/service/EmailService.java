package com.cine.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

@Service
public class EmailService {

    private final SesClient sesClient;

    @Value("${aws.ses.from-email}")
    private String fromEmail;

    public EmailService(@Value("${aws.ses.access-key}") String accessKey,
                        @Value("${aws.ses.secret-key}") String secretKey,
                        @Value("${aws.ses.region}") String region) {
        this.sesClient = SesClient.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
                .build();
    }

    public void sendReservationEmail(String toEmail, String subject, String body) {
        SendEmailRequest emailRequest = SendEmailRequest.builder()
                .source(fromEmail)
                .destination(Destination.builder().toAddresses(toEmail).build())
                .message(Message.builder()
                        .subject(Content.builder().data(subject).build())
                        .body(Body.builder().text(Content.builder().data(body).build()).build())
                        .build())
                .build();

        sesClient.sendEmail(emailRequest);
    }
}
