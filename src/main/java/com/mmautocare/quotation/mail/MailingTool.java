package com.mmautocare.quotation.mail;

import com.mmautocare.quotation.entity.Quotation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.ResourceUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class MailingTool {

    private static final boolean IS_MULTIPART = true;
    private static final String LOGO_CLASSPATH = "classpath:static/kakao-logo.jpg";
    private static final String LOGO_CONTENT_ID = "logo";
    private static final String MANAGER_EMAIL_ADDRESS = "info@lvps.ca";

    private final MimeMessage messageToSend;
    private final MimeMessageHelper messageHelper;
    private final Quotation quotation;

    public static MailingTool preSet(MimeMessage mimeMessage, Quotation quotation) throws MessagingException {
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, IS_MULTIPART, StandardCharsets.UTF_8.name());
        messageHelper.setSubject(quotation.makeMailSubject());
        return new MailingTool(mimeMessage, messageHelper, quotation);
    }

    public MimeMessage createMessageToCustomer() throws MessagingException, IOException {
        this.messageHelper.setTo(this.quotation.getEmail());
        this.messageHelper.setText(this.quotation.makeCustomerMailText());
        addInlineImage(this.messageHelper);
        return this.messageToSend;
    }

    private void addInlineImage(MimeMessageHelper messageHelper) throws IOException, MessagingException {
        File logoFile = ResourceUtils.getFile(LOGO_CLASSPATH);
        messageHelper.addInline(LOGO_CONTENT_ID, new FileSystemResource(logoFile));
    }

    public MimeMessage createMessageToManager() throws MessagingException {
        // TODO: 2021-04-20 이메일주소 바꾸기
        this.messageHelper.setTo("ebseud6135@gmail.com");
        this.messageHelper.setText(this.quotation.makeManagerMailText());
        return this.messageToSend;
    }
}
