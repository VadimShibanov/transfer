package com.bank.transfer.mapper;

import com.bank.transfer.dto.PhoneTransferDTO;
import com.bank.transfer.entity.PhoneTransfer;
import org.mapstruct.Mapper;
import java.util.List;

/**
 * Вспомогательный класс для преобразования(маппинга) сущности в DTO-объект
 */

@Mapper(componentModel = "spring")
public interface PhoneTransferMapper {
    static PhoneTransferMapper getInstance() {
        return new PhoneTransferMapperImpl();
    }
    PhoneTransfer DTOToPhoneTransfer (PhoneTransferDTO phoneTransferDTO);
    List<PhoneTransferDTO> PhoneTransferListToDTO (List<PhoneTransfer> phoneTransferList);
}
