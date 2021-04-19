package com.mmautocare.quotation.repository;

import com.mmautocare.quotation.entity.Quotation;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuotationRepository extends JpaRepository<Quotation, Long> {

    @EntityGraph(attributePaths = "services")
    Optional<Quotation> findById(Long id);
}
