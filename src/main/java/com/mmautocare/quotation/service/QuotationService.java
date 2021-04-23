package com.mmautocare.quotation.service;

import com.mmautocare.quotation.entity.Quotation;
import com.mmautocare.quotation.repository.QuotationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuotationService {

    private final QuotationRepository quotationRepository;

    @Transactional
    public Quotation save(Quotation quotation) {
        return quotationRepository.save(quotation);
    }

    public Quotation findById(Long id) {
        return quotationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id는 존재하지 않습니다."));
    }

    public List<Quotation> findAll() {
        return quotationRepository.findAll();
    }

    public void deleteAll() {
        quotationRepository.deleteAll();
    }
}
