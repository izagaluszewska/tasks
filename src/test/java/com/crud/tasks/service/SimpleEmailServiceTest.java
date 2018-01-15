package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {
    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private MailCreatorService mailCreatorService;

    @Test
    public void shouldSendBuildTrelloCardMessage() {
        //Given
        Mail mail = new Mail("test@test.com", "Test", "Test Message", null);
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()), true);
        };
        when(mailCreatorService.createMimeMessageBuildTrelloCard(mail)).thenReturn(mimeMessagePreparator);
        //When
        simpleEmailService.sendBuildTrelloCardMessage(mail);
        //Then
        verify(javaMailSender, times(1)).send(mimeMessagePreparator);
    }

    @Test
    public void shouldSendInfoTaskAmountMessage() {
        //Given
        Mail mail = new Mail("test@test.com", "Test", "Test Message", null);
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.infoTaskAmountEmail(mail.getMessage()), true);
        };
        when(mailCreatorService.createMimeMessageInfoTaskAmount(mail)).thenReturn(mimeMessagePreparator);
        //When
        simpleEmailService.sendInfoTaskAmountMessage(mail);
        //Then
        verify(javaMailSender, times(1)).send(mimeMessagePreparator);
    }
}