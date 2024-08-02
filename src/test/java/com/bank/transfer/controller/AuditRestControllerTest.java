package com.bank.transfer.controller;

import com.bank.transfer.dto.AuditDTO;
import com.bank.transfer.dto.PhoneTransferDTO;
import com.bank.transfer.entity.Audit;
import com.bank.transfer.entity.PhoneTransfer;
import com.bank.transfer.mapper.AuditMapper;
import com.bank.transfer.mapper.PhoneTransferMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuditRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    AuditMapper auditMapper;
    Audit audit;
    AuditDTO auditDTO;
    List<AuditDTO> DTOList = new ArrayList<>();
    String id;

    @BeforeAll
    void prepare() {
        auditDTO = AuditDTO
                .builder()
                .entityType("Account")
                .operationType("Deposit")
                .createdBy("Senior Inspector")
                .modifiedBy("Junior Inspector")
                .createdAt(ZonedDateTime.now())
                .modifiedAt(ZonedDateTime.now())
                .newEntityJson("empty new json")
                .entityJson("new json")
                .build();

        DTOList.add(auditDTO);

        id = auditDTO.getEntityType();

        audit = Audit.builder()
                .entityType("Account")
                .operationType("Deposit")
                .createdBy("Senior Inspector")
                .modifiedBy("Junior Inspector")
                .createdAt(ZonedDateTime.now())
                .modifiedAt(ZonedDateTime.now())
                .newEntityJson("empty new json")
                .entityJson("new json")
                .build();
    }

    @Test
    void addAudit() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/audit")
                        .content(objectMapper.writeValueAsString(audit))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getAuditByEntityType() throws Exception {

        Mockito.doReturn(DTOList)
                .when(auditMapper).AuditListToDTO(Mockito.any());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/audit/{id}",id).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$").exists())
                .andExpect(status().isOk());
    }
}