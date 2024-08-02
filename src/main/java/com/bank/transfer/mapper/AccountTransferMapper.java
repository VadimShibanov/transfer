package com.bank.transfer.mapper;

import com.bank.transfer.dto.AccountTransferDTO;
import com.bank.transfer.entity.AccountTransfer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.mapstruct.Mapper;
import java.util.List;

/**
 * Вспомогательный класс для преобразования(маппинга) сущности в DTO-объект
 */
@Mapper(componentModel = "spring")
public interface AccountTransferMapper {
    static AccountTransferMapper getInstance() {
        return new AccountTransferMapperImpl();
    }
    AccountTransfer DTOToAccountTransfer (AccountTransferDTO accountTransferDTO);
    List<AccountTransferDTO> AccountTransferListToDTO (List<AccountTransfer> accountTransferList);
}
