package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;


import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;


@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void shouldSentEmail(){
        //Given
        Mail mail = new Mail("test@test.com", "Test", "Message", "testa@test.com");

        //when
        simpleEmailService.send(mail);

        //Then
        verify(javaMailSender, times(1)).send(any(MimeMessagePreparator.class));
    }

}