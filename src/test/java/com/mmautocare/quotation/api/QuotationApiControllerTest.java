package com.mmautocare.quotation.api;

import com.mmautocare.quotation.service.QuotationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(QuotationApiController.class)
@AutoConfigureMockMvc
class QuotationApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuotationService quotationService;

    @DisplayName("")
    @Test
    void name() {
        when(quotationService.save(any())).thenThrow(new IllegalArgumentException());
    }
}