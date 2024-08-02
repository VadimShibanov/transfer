package com.bank.transfer.mapper;

import com.bank.transfer.dto.PhoneTransferDTO;
import com.bank.transfer.entity.PhoneTransfer;
import com.bank.transfer.mapper.PhoneTransferMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
class PhoneTransferMapperTest {

    private PhoneTransferMapper phoneTransferMapper = PhoneTransferMapper.getInstance();

    @Test
    void DTOToPhoneTransfer() {
        PhoneTransferDTO phoneTransferDTO = PhoneTransferDTO.builder().build();
        PhoneTransfer actualResult = phoneTransferMapper.DTOToPhoneTransfer(phoneTransferDTO);
        PhoneTransfer expectedResult = PhoneTransfer.builder().build();

        Assertions.assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void cardTransferListToDTO() {

        List<PhoneTransfer> testList = new ArrayList<>();
        testList.add(PhoneTransfer.builder().build());

        List<PhoneTransferDTO> actualResult = phoneTransferMapper.PhoneTransferListToDTO(testList);

        List<PhoneTransferDTO> testDTOList = new ArrayList<>();
        testDTOList.add(PhoneTransferDTO.builder().build());

        List<PhoneTransferDTO> expectedResult = testDTOList;

        Assertions.assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void getInstance() {

        PhoneTransferMapper actualResult = PhoneTransferMapper.getInstance();
        PhoneTransferMapper expectedResult = new PhoneTransferMapperImpl();

        Assertions.assertThat(actualResult.getClass()).isEqualTo(expectedResult.getClass());
    }
}