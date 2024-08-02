package com.bank.transfer.mapper;

import com.bank.transfer.dto.AuditDTO;
import com.bank.transfer.entity.Audit;
import com.bank.transfer.mapper.AuditMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
class AuditMapperTest {

    private AuditMapper auditMapper = AuditMapper.getInstance();

    @Test
    void DTOToAudit() {
        AuditDTO auditDTO = AuditDTO.builder().build();
        Audit actualResult = auditMapper.DTOToAudit(auditDTO);
        Audit expectedResult = Audit.builder().build();

        Assertions.assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void auditListToDTO() {
    }

    @Test
    void getInstance() {

        AuditMapper actualResult = AuditMapper.getInstance();
        AuditMapper expectedResult = new AuditMapperImpl();

        Assertions.assertThat(actualResult.getClass()).isEqualTo(expectedResult.getClass());
    }
}