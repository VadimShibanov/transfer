package com.bank.transfer.repository;

import com.bank.transfer.entity.CardTransfer;
import com.bank.transfer.repository.CardTransferRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CardTransferRepositoryTest {

    @Autowired
    private CardTransferRepository cardTransferRepository;

    CardTransfer cardTransfer;

    @BeforeAll
    void prepare() {

        cardTransfer = CardTransfer.builder()
                .cardNumber(2345L)
                .amount(new BigDecimal(404))
                .purpose("Spring Data JPA test transfer")
                .accountDetails(5555L)
                .build();
        cardTransferRepository.save(cardTransfer);
    }

    @Test
    void findByCardNumber() {
        Assertions.assertThat(cardTransfer.getId()).isGreaterThan(0);

        List<CardTransfer> resultList = cardTransferRepository
                .findByCardNumber(cardTransfer.getCardNumber());

        Assertions.assertThat(resultList.get(0).getCardNumber())
                .isEqualTo(cardTransfer.getCardNumber());
    }
}