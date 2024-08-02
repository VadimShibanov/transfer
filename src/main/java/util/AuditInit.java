package util;

import com.bank.transfer.dto.AuditDTO;
import com.bank.transfer.entity.Audit;
import com.bank.transfer.service.AuditService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
@AllArgsConstructor
public class AuditInit {

    private final AuditService auditService;

    private final ModelMapper modelMapper;

    @PostConstruct
    private void dbinit() {

        AuditDTO auditDTO = new AuditDTO();

        auditDTO.setEntityType("Account");
        auditDTO.setOperationType("Deposit");
        auditDTO.setCreatedBy("Senior Inspector");
        auditDTO.setModifiedBy("Junior Inspector");
        auditDTO.setCreatedAt(ZonedDateTime.now());
        auditDTO.setModifiedAt(ZonedDateTime.now());
        auditDTO.setNewEntityJson("empty new json");
        auditDTO.setEntityJson("new json");

        auditService.add(modelMapper.map(auditDTO, Audit.class));
    }
}

