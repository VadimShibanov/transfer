package com.bank.transfer.repository;

import com.bank.transfer.entity.PhoneTransfer;
import com.bank.transfer.repository.PhoneTransferRepository;
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
class PhoneTransferRepositoryTest {

    @Autowired
    private PhoneTransferRepository phoneTransferRepository;

    PhoneTransfer phoneTransfer;

    @BeforeAll
    void prepare() {

        phoneTransfer = PhoneTransfer.builder()
                .phoneNumber(89212345678L)
                .amount(new BigDecimal(404))
                .purpose("Spring Data JPA test transfer")
                .accountDetails(5555L)
                .build();
        phoneTransferRepository.save(phoneTransfer);
    }

    @Test
    void findByPhoneNumber() {
        Assertions.assertThat(phoneTransfer.getId()).isGreaterThan(0);

        List<PhoneTransfer> resultList = phoneTransferRepository
                .findByPhoneNumber(phoneTransfer.getPhoneNumber());

        Assertions.assertThat(resultList.get(0).getPhoneNumber())
                .isEqualTo(phoneTransfer.getPhoneNumber());
    }
}