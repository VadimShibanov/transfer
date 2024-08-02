package util;

import com.bank.transfer.dto.PhoneTransferDTO;
import com.bank.transfer.entity.PhoneTransfer;
import com.bank.transfer.service.PhoneTransferService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Component
@AllArgsConstructor
public class PhoneTransferInit {

    private final PhoneTransferService phoneTransferService;
    private final ModelMapper modelMapper;

    @PostConstruct
    private void dbinit() {

        PhoneTransferDTO phoneTransferDTO = new PhoneTransferDTO();
        phoneTransferDTO.setPhoneNumber(89215556677L);
        phoneTransferDTO.setAmount(new BigDecimal(250));
        phoneTransferDTO.setPurpose("Init phone deposit");
        phoneTransferDTO.setAccountDetails(200L);

        phoneTransferService.add(modelMapper.map(phoneTransferDTO, PhoneTransfer.class));
    }
}

