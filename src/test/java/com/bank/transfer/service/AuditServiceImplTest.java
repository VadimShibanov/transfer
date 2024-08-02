package com.bank.transfer.service;

import com.bank.transfer.entity.Audit;
import com.bank.transfer.entity.CardTransfer;
import com.bank.transfer.repository.AuditRepository;
import com.bank.transfer.repository.CardTransferRepository;
import com.bank.transfer.service.AuditServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuditServiceImplTest {

    private Audit audit;
    @Mock(lenient = true)
    AuditRepository auditRepository;

    @InjectMocks
    AuditServiceImpl auditServiceImpl;

    @BeforeEach
    void prepare() {
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

        List<Audit> testlist = new ArrayList<>();
        testlist.add(audit);

        Mockito.doReturn(testlist).when(auditRepository).findByEntityType(Mockito.any());
    }

    @Test
    void add() {
        auditServiceImpl.add(audit);
        List<Audit> addResult = auditRepository.findByEntityType(audit.getEntityType());
        Assertions.assertThat(addResult.get(0).getEntityType()).isEqualTo(audit.getEntityType());
    }

    @Test
    void delete() {
        auditServiceImpl.add(audit);
        auditServiceImpl.delete(audit);

        Mockito.doReturn(new ArrayList<Audit>()).when(auditRepository).findByEntityType(Mockito.any());

        List<Audit> deleteResult = auditRepository.findByEntityType(audit.getEntityType());
        Assertions.assertThat(deleteResult).isEmpty();
    }

    @Test
    void getAuditsByEntityType() {
        auditServiceImpl.add(audit);
        List<Audit> addResult = auditRepository.findByEntityType(audit.getEntityType());
        Assertions.assertThat(addResult.get(0).getEntityType()).isEqualTo(audit.getEntityType());
    }
}