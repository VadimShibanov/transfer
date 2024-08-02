package util;

import com.bank.transfer.dto.CardTransferDTO;
import com.bank.transfer.entity.CardTransfer;
import com.bank.transfer.service.CardTransferService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Component
@AllArgsConstructor
public class CardTransferInit {

    private final CardTransferService cardTransferService;
    private final ModelMapper modelMapper;

    @PostConstruct
    private void dbinit() {

        CardTransferDTO cardTransferDTO = new CardTransferDTO();
        cardTransferDTO.setCardNumber(2222333344445555L);
        cardTransferDTO.setAmount(new BigDecimal(10000));
        cardTransferDTO.setPurpose("Init card deposit 01");
        cardTransferDTO.setAccountDetails(100L);

        cardTransferService.add(modelMapper.map(cardTransferDTO, CardTransfer.class));

        CardTransferDTO cardTransferDTO2 = new CardTransferDTO();
        cardTransferDTO2.setCardNumber(2222333344446666L);
        cardTransferDTO2.setAmount(new BigDecimal(12000));
        cardTransferDTO2.setPurpose("Init card deposit 02");
        cardTransferDTO2.setAccountDetails(101L);

        cardTransferService.add(modelMapper.map(cardTransferDTO2, CardTransfer.class));

        CardTransferDTO cardTransferDTO3 = new CardTransferDTO();
        cardTransferDTO3.setCardNumber(2222333344447777L);
        cardTransferDTO3.setAmount(new BigDecimal(14000));
        cardTransferDTO3.setPurpose("Init card deposit 03");
        cardTransferDTO3.setAccountDetails(102L);

        cardTransferService.add(modelMapper.map(cardTransferDTO3, CardTransfer.class));
    }
}

