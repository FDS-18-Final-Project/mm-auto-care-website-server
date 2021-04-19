package com.mmautocare.quotation.repository;

import com.mmautocare.quotation.entity.Quotation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class QuotationRepositoryTest {

    @Autowired
    private QuotationRepository quotationRepository;

    @DisplayName("save 테스트")
    @Test
    void save() {
        Quotation expected = Quotation.builder()
                .id(null)
                .email("asd@naver.com")
                .make("make")
                .message("message")
                .model("model")
                .phoneNumber("010-1111-2222")
                .services(new HashSet<>(Arrays.asList("a", "b", "c")))
                .build();

        Quotation actual = quotationRepository.save(expected);

        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }
}