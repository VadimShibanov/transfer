package com.bank.transfer.mapper;

import com.bank.transfer.dto.AccountTransferDTO;
import com.bank.transfer.entity.AccountTransfer;
import com.bank.transfer.mapper.AccountTransferMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
class AccountTransferMapperTest {
    private AccountTransferMapper accountTransferMapper = AccountTransferMapper.getInstance();
    @Test
    void DTOToAccountTransfer() {

        AccountTransferDTO accountTransferDTO = AccountTransferDTO.builder().build();
        AccountTransfer actualResult = accountTransferMapper.DTOToAccountTransfer(accountTransferDTO);
        AccountTransfer expectedResult = AccountTransfer.builder().build();

        Assertions.assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void accountTransferListToDTO() {

        List<AccountTransfer> testList = new ArrayList<>();
        testList.add(AccountTransfer.builder().build());

        List<AccountTransferDTO> actualResult = accountTransferMapper.AccountTransferListToDTO(testList);

        List<AccountTransferDTO> testDTOList = new ArrayList<>();
        testDTOList.add(AccountTransferDTO.builder().build());

        List<AccountTransferDTO> expectedResult = testDTOList;

        Assertions.assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void getInstance() {

        AccountTransferMapper actualResult = AccountTransferMapper.getInstance();
        AccountTransferMapper expectedResult = new AccountTransferMapperImpl();

        Assertions.assertThat(actualResult.getClass()).isEqualTo(expectedResult.getClass());
    }
}