package com.mmautocare.quotation.mail;

import com.mmautocare.quotation.entity.Quotation;
import com.mmautocare.quotation.service.QuotationService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@RequiredArgsConstructor
@Service
public class QuotationMailService {

    private final QuotationService quotationService;
    private final JavaMailSender mailSender;

    public boolean sendMails(Long id) {
        Quotation quotation = quotationService.findById(id);
        try {
            sendMailToCustomer(MailingTool.preSet(mailSender.createMimeMessage(), quotation));
            sendMailToManager(MailingTool.preSet(mailSender.createMimeMessage(), quotation));
            return true;
        } catch (IOException | MessagingException ex) {
            throw new RuntimeException("Mail에서 예상치 못한 예외가 발생했습니다.");
        }
    }

    private void sendMailToCustomer(MailingTool mailingTool) throws MessagingException, IOException {
        MimeMessage messageToCustomer = mailingTool.createMessageToCustomer();
        mailSender.send(messageToCustomer);
    }

    private void sendMailToManager(MailingTool mailingTool) throws MessagingException {
        MimeMessage messageToManager = mailingTool.createMessageToManager();
        mailSender.send(messageToManager);
    }

}