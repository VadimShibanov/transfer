package com.bank.transfer.mapper;

import com.bank.transfer.dto.CardTransferDTO;
import com.bank.transfer.entity.CardTransfer;
import com.bank.transfer.mapper.CardTransferMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
class CardTransferMapperTest {

    private CardTransferMapper cardTransferMapper = CardTransferMapper.getInstance();

    @Test
    void DTOToCardTransfer() {
        CardTransferDTO cardTransferDTO = CardTransferDTO.builder().build();
        CardTransfer actualResult = cardTransferMapper.DTOToCardTransfer(cardTransferDTO);
        CardTransfer expectedResult = CardTransfer.builder().build();

        Assertions.assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void cardTransferListToDTO() {

        List<CardTransfer> testList = new ArrayList<>();
        testList.add(CardTransfer.builder().build());

        List<CardTransferDTO> actualResult = cardTransferMapper.CardTransferListToDTO(testList);

        List<CardTransferDTO> testDTOList = new ArrayList<>();
        testDTOList.add(CardTransferDTO.builder().build());

        List<CardTransferDTO> expectedResult = testDTOList;

        Assertions.assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void getInstance() {

        CardTransferMapper actualResult = CardTransferMapper.getInstance();
        CardTransferMapper expectedResult = new CardTransferMapperImpl();

        Assertions.assertThat(actualResult.getClass()).isEqualTo(expectedResult.getClass());
    }


}