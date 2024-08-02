package com.bank.transfer.service;

import com.bank.transfer.entity.AccountTransfer;
import com.bank.transfer.entity.CardTransfer;
import com.bank.transfer.repository.AccountTransferRepository;
import com.bank.transfer.repository.CardTransferRepository;
import com.bank.transfer.service.CardTransferServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CardTransferServiceImplTest {

    private CardTransfer cardTransfer;

    @Mock(lenient = true)
    CardTransferRepository cardTransferRepository;

    @InjectMocks
    CardTransferServiceImpl cardTransferServiceImpl;

    @BeforeEach
    void prepare() {
        cardTransfer = CardTransfer.builder()
                .cardNumber(2345L)
                .amount(new BigDecimal(404))
                .purpose("Spring Data JPA test transfer")
                .accountDetails(5555L)
                .build();

        List<CardTransfer> testlist = new ArrayList<>();
        testlist.add(cardTransfer);

        Mockito.doReturn(testlist).when(cardTransferRepository).findByCardNumber(Mockito.any());
    }

    @Test
    void add() {
        cardTransferServiceImpl.add(cardTransfer);
        List<CardTransfer> addResult = cardTransferRepository.findByCardNumber(cardTransfer.getCardNumber());
        Assertions.assertThat(addResult.get(0).getCardNumber()).isEqualTo(cardTransfer.getCardNumber());
    }

    @Test
    void delete() {
        cardTransferServiceImpl.add(cardTransfer);
        cardTransferServiceImpl.delete(cardTransfer);

        Mockito.doReturn(new ArrayList<CardTransfer>()).when(cardTransferRepository).findByCardNumber(Mockito.any());

        List<CardTransfer> deleteResult = cardTransferRepository.findByCardNumber(cardTransfer.getCardNumber());
        Assertions.assertThat(deleteResult).isEmpty();
    }

    @Test
    void getCardTransfersByCardNumber() {
        cardTransferServiceImpl.add(cardTransfer);
        List<CardTransfer> addResult = cardTransferRepository.findByCardNumber(cardTransfer.getCardNumber());
        Assertions.assertThat(addResult.get(0).getCardNumber()).isEqualTo(cardTransfer.getCardNumber());
    }
}