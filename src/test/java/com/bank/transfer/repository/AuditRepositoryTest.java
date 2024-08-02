package com.bank.transfer.repository;

import com.bank.transfer.entity.Audit;
import com.bank.transfer.repository.AuditRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuditRepositoryTest {

    @Autowired
    private AuditRepository auditRepository;
    Audit audit;

    @BeforeAll
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
        auditRepository.save(audit);
    }

    @Test
    void findByEntityType() {

        List<Audit> resultList = auditRepository
                .findByEntityType(audit.getEntityType());

        Assertions.assertThat(resultList.get(0).getEntityType())
                .isEqualTo(audit.getEntityType());
    }
}