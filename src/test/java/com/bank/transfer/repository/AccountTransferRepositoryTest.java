package com.bank.transfer.repository;

import com.bank.transfer.entity.AccountTransfer;
import com.bank.transfer.repository.AccountTransferRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccountTransferRepositoryTest {
    @Autowired
    private AccountTransferRepository accountTransferRepository;
    AccountTransfer accountTransfer;

    @BeforeAll
    void prepare() {

        accountTransfer = AccountTransfer.builder()
                .accountNumber(2345L)
                .amount(new BigDecimal(404))
                .purpose("Spring Data JPA test account transfer")
                .accountDetails(5555L)
                .build();
        accountTransferRepository.save(accountTransfer);
    }

    @Test
    void findByAccountNumber() {
        Assertions.assertThat(accountTransfer.getId()).isGreaterThan(0);

        List<AccountTransfer> resultList = accountTransferRepository
                .findByAccountNumber(accountTransfer.getAccountNumber());

        Assertions.assertThat(resultList.get(0).getAccountNumber())
                .isEqualTo(accountTransfer.getAccountNumber());
    }

    @Test
    void existsByAccountNumber() {
        boolean checkResult = accountTransferRepository
                .existsByAccountNumber(accountTransfer.getAccountNumber());

        assertTrue(checkResult);

        List<AccountTransfer> resultList = accountTransferRepository
                .findByAccountNumber(accountTransfer.getAccountNumber());

        Assertions.assertThat(resultList.get(0).getAccountNumber())
                .isEqualTo(accountTransfer.getAccountNumber());
    }
}