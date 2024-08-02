package util;

import com.bank.transfer.dto.AccountTransferDTO;
import com.bank.transfer.entity.AccountTransfer;
import com.bank.transfer.service.AccountTransferService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Component
@AllArgsConstructor
public class AccountTransferInit {

    private final AccountTransferService accountTransferService;

    private final ModelMapper modelMapper;

    @PostConstruct
    private void dbinit() {

        AccountTransferDTO accountTransferDTO = new AccountTransferDTO();
        accountTransferDTO.setAccountNumber(123456L);
        accountTransferDTO.setAmount(new BigDecimal(88888L));
        accountTransferDTO.setPurpose("Init account deposit");
        accountTransferDTO.setAccountDetails(300L);

        accountTransferService.add(modelMapper.map(accountTransferDTO, AccountTransfer.class));
    }
}
