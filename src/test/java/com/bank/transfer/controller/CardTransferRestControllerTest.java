package com.bank.transfer.controller;

import com.bank.transfer.dto.AccountTransferDTO;
import com.bank.transfer.dto.CardTransferDTO;
import com.bank.transfer.entity.AccountTransfer;
import com.bank.transfer.entity.CardTransfer;
import com.bank.transfer.mapper.AccountTransferMapper;
import com.bank.transfer.mapper.CardTransferMapper;
import com.bank.transfer.service.CardTransferService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CardTransferRestControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    CardTransferService cardTransferService;
    CardTransfer cardTransfer;
    List<CardTransfer> testList = new ArrayList<>();
    Long id;
    @BeforeAll
    void prepare() {
        cardTransfer = CardTransfer.builder()
                .cardNumber(2345L)
                .amount(new BigDecimal(404))
                .purpose("Spring Data JPA test transfer")
                .accountDetails(5555L)
                .build();

        testList = List.of(cardTransfer);

        id = cardTransfer.getCardNumber();
    }
    @Test
    void addCardTransfer() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/card")
                        .content(objectMapper.writeValueAsString(cardTransfer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    void getCardTransfersByCardNumber() throws Exception {

        when(cardTransferService
                        .getCardTransfersByCardNumber(id))
                .thenReturn(testList);

        mockMvc.perform(get("/card/{id}", id).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$[*].cardNumber", contains(2345)))
                .andExpect(status().isOk());
    }
}