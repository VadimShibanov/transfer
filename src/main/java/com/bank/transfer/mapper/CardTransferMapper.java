package com.bank.transfer.mapper;

import com.bank.transfer.dto.CardTransferDTO;
import com.bank.transfer.entity.CardTransfer;
import org.mapstruct.Mapper;
import java.util.List;

/**
 * Вспомогательный класс для преобразования(маппинга) сущности в DTO-объект
 */
@Mapper(componentModel = "spring")
public interface CardTransferMapper {
    static CardTransferMapper getInstance() {
        return new CardTransferMapperImpl();
    }
    CardTransfer DTOToCardTransfer (CardTransferDTO cardTransferDTO);
    List<CardTransferDTO> CardTransferListToDTO (List<CardTransfer> cardTransferList);
}
