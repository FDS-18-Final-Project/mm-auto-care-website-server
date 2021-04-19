package com.mmautocare.quotation.mail;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MailFormat {

    CUSTOMER_MAIL_FORMAT("Dear %s %n" +
            "Your quote request has been successfully submitted. Thank you for contacting us. %n" +
            "We will get back to you as soon as possible. %n" +
            "%n" +
            "Sincerely, %n" +
            "Maxim Malyshau %n" +
            "General Manager at LVPS"),
    MANAGER_MAIL_FORMAT("Name: %s %n" +
            "Email: %s %n" +
            "Phone number: %s %n" +
            "Make: %s %n" +
            "Model: %s %n" +
            "Year: %s %n" +
            "Services: %s %n" +
            "Message: %s"),
    SUBJECT_FORMAT("%o_Quote");

    private final String format;

}
