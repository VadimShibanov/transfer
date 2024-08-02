package com.bank.transfer.controller;

import com.bank.transfer.entity.PhoneTransfer;
import com.bank.transfer.service.PhoneTransferService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PhoneTransferRestControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    PhoneTransferService phoneTransferService;
    PhoneTransfer phoneTransfer;
    List<PhoneTransfer> testList = new ArrayList<>();
    Long id;
    @BeforeAll
    void prepare() {
        phoneTransfer = PhoneTransfer.builder()
                .phoneNumber(8921234L)
                .amount(new BigDecimal(404))
                .purpose("Spring Data JPA test transfer")
                .accountDetails(5555L)
                .build();

        testList = List.of(phoneTransfer);

        id = phoneTransfer.getPhoneNumber();
    }
    @Test
    void addPhoneTransfer() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/phone")
                        .content(objectMapper.writeValueAsString(phoneTransfer))
                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getPhoneTransfersByPhoneNumber() throws Exception {
        when(phoneTransferService
                .getPhoneTransfersByPhoneNumber(id))
                .thenReturn(testList);

        mockMvc.perform(get("/phone/{id}", id).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$[*].phoneNumber", contains(8921234)))
                .andExpect(status().isOk());
    }
}