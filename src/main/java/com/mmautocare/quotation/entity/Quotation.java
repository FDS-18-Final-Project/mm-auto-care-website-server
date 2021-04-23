package com.mmautocare.quotation.entity;

import com.mmautocare.quotation.mail.MailFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Quotation {

    private static final String SERVICES_DELIMITER = ",";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Email(message = "email 형식에 맞지 않습니다.")
    private String email;

    private String phoneNumber;
    private String make;
    private String model;
    private String year;

    @ElementCollection
    private Set<String> services;

    private String message;

    public String makeMailSubject() {
        return String.format(MailFormat.SUBJECT_FORMAT.getFormat(), this.id);
    }

    public String makeCustomerMailText() {
        return String.format(MailFormat.CUSTOMER_MAIL_FORMAT.getFormat(), this.fullName);
    }

    public String makeManagerMailText() {
        return String.format(MailFormat.MANAGER_MAIL_FORMAT.getFormat(),
                this.fullName,
                this.email,
                this.phoneNumber,
                this.make,
                this.model,
                this.year,
                String.join(SERVICES_DELIMITER, this.services),
                this.message);
    }

}
