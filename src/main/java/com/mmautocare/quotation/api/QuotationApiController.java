package com.mmautocare.quotation.api;

import com.mmautocare.quotation.entity.Quotation;
import com.mmautocare.quotation.mail.QuotationMailService;
import com.mmautocare.quotation.service.QuotationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quotations")
public class QuotationApiController {

    private final QuotationService quotationService;
    private final QuotationMailService quotationMailService;

    @PostMapping
    public ResponseEntity<Long> save(Quotation quotation) {
        Quotation save = quotationService.save(quotation);
        return ResponseEntity.created(URI.create("/" + save.getId())).build();
    }

    @GetMapping("/{id}/send-mails")
    public ResponseEntity<Boolean> sendMails(@PathVariable Long id) {
        return ResponseEntity.ok(quotationMailService.sendMails(id));
    }

}