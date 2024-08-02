package com.bank.transfer.service;

import com.bank.transfer.entity.AccountTransfer;
import com.bank.transfer.repository.AccountTransferRepository;
import com.bank.transfer.service.AccountTransferServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AccountTransferServiceImplTest {

    private AccountTransfer accountTransfer;
    @Mock(lenient = true)
    AccountTransferRepository accountTransferRepository;
    @InjectMocks
    AccountTransferServiceImpl accountTransferServiceImpl;
    @BeforeEach
    void prepare() {
        accountTransfer = AccountTransfer
                .builder()
                .accountNumber(100L)
                .amount(new BigDecimal(3333))
                .purpose("Unit test deposit")
                .accountDetails(100L)
                .build();

        Mockito.doReturn(true).when(accountTransferRepository).existsByAccountNumber(Mockito.any());
    }
    @Test
    void add() {
        accountTransferServiceImpl.add(accountTransfer);
        boolean addResult = accountTransferServiceImpl.checkIfExistsByAccountNumber(accountTransfer.getAccountNumber());
        assertTrue(addResult);
    }
    @Test
    void deleteAllWithThisAccountNumber() {
        accountTransferServiceImpl.add(accountTransfer);
        accountTransferServiceImpl.deleteAllWithThisAccountNumber(accountTransfer.getAccountNumber());
        Mockito.doReturn(false).when(accountTransferRepository).existsByAccountNumber(Mockito.any());
        boolean checkIfExists = accountTransferServiceImpl.checkIfExistsByAccountNumber(accountTransfer.getAccountNumber());
        assertFalse(checkIfExists);
    }
    @Test
    void checkIfExistsByAccountNumber() {
        accountTransferServiceImpl.add(accountTransfer);
        boolean result = accountTransferServiceImpl.checkIfExistsByAccountNumber(accountTransfer.getAccountNumber());
        assertTrue(result);
    }
}