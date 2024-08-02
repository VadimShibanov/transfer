package com.bank.transfer.service;

import com.bank.transfer.entity.CardTransfer;
import com.bank.transfer.entity.PhoneTransfer;
import com.bank.transfer.repository.CardTransferRepository;
import com.bank.transfer.repository.PhoneTransferRepository;
import com.bank.transfer.service.PhoneTransferServiceImpl;
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
class PhoneTransferServiceImplTest {

    private PhoneTransfer phoneTransfer;

    @Mock(lenient = true)
    PhoneTransferRepository phoneTransferRepository;

    @InjectMocks
    PhoneTransferServiceImpl phoneTransferServiceImpl;

    @BeforeEach
    void prepare() {
        phoneTransfer = PhoneTransfer.builder()
                .phoneNumber(89212345678L)
                .amount(new BigDecimal(404))
                .purpose("Spring Data JPA test transfer")
                .accountDetails(5555L)
                .build();

        List<PhoneTransfer> testlist = new ArrayList<>();
        testlist.add(phoneTransfer);

        Mockito.doReturn(testlist).when(phoneTransferRepository).findByPhoneNumber(Mockito.any());
    }

    @Test
    void add() {
        phoneTransferServiceImpl.add(phoneTransfer);
        List<PhoneTransfer> addResult = phoneTransferRepository.findByPhoneNumber(phoneTransfer.getPhoneNumber());
        Assertions.assertThat(addResult.get(0).getPhoneNumber()).isEqualTo(phoneTransfer.getPhoneNumber());
    }

    @Test
    void delete() {
        phoneTransferServiceImpl.add(phoneTransfer);
        phoneTransferServiceImpl.delete(phoneTransfer);

        Mockito.doReturn(new ArrayList<PhoneTransfer>()).when(phoneTransferRepository).findByPhoneNumber(Mockito.any());

        List<PhoneTransfer> deleteResult = phoneTransferRepository.findByPhoneNumber(phoneTransfer.getPhoneNumber());
        Assertions.assertThat(deleteResult).isEmpty();
    }

    @Test
    void getPhoneTransfersByPhoneNumber() {
        phoneTransferServiceImpl.add(phoneTransfer);
        List<PhoneTransfer> addResult = phoneTransferRepository.findByPhoneNumber(phoneTransfer.getPhoneNumber());
        Assertions.assertThat(addResult.get(0).getPhoneNumber()).isEqualTo(phoneTransfer.getPhoneNumber());
    }
}