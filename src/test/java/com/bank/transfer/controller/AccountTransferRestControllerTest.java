package com.bank.transfer.controller;

import com.bank.transfer.entity.AccountTransfer;
import com.bank.transfer.service.AccountTransferService;
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

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccountTransferRestControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    AccountTransferService accountTransferService;
    List<AccountTransfer> testList = new ArrayList<>();
    AccountTransfer accountTransfer;
    Long id;
    @BeforeAll
    void prepare() {

        accountTransfer = AccountTransfer.builder()
                .accountNumber(2345L)
                .amount(new BigDecimal(404))
                .purpose("Spring Data JPA test transfer")
                .accountDetails(5555L)
                .build();

        testList = List.of(accountTransfer);

        id = accountTransfer.getAccountNumber();
    }
    @Test
    void getAccountTransfersByAccountNumber() throws Exception {

        when(accountTransferService
                .getAccountTransfersByAccountNumber(id))
                .thenReturn(testList);

        mockMvc.perform(get("/account/{id}", id).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$[*].accountNumber",contains(2345)))
                .andExpect(status().isOk());
    }

    @Test
    void addAccountTransfer() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

         mockMvc.perform(post("/account")
                        .content(objectMapper.writeValueAsString(accountTransfer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteAccountTransfer() throws Exception {

        when(accountTransferService.checkIfExistsByAccountNumber(accountTransfer.getAccountNumber()))
                .thenReturn(true);

        mockMvc.perform(delete("/account/" + accountTransfer.getAccountNumber()))
                .andDo(print())
                .andExpect(status().isOk());
    }
}