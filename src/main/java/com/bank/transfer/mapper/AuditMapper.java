package com.bank.transfer.mapper;

import com.bank.transfer.dto.AuditDTO;
import com.bank.transfer.entity.Audit;
import org.mapstruct.Mapper;
import java.util.List;

/**
 * Вспомогательный класс для преобразования(маппинга) сущности в DTO-объект
 */
@Mapper(componentModel = "spring")
public interface AuditMapper {
    static AuditMapper getInstance() {
        return new AuditMapperImpl();
    }
    Audit DTOToAudit (AuditDTO auditDTO);
    List<AuditDTO> AuditListToDTO (List<Audit> auditList);
}
