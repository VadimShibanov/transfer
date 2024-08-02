package com.bank.transfer.controller;

import com.bank.transfer.dto.CardTransferDTO;
import com.bank.transfer.mapper.CardTransferMapper;
import com.bank.transfer.service.CardTransferService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер операций по оплате с помощью Дебитной/Кредитной карты
 */
@org.springframework.web.bind.annotation.RestController
@AllArgsConstructor
public class CardTransferRestController {
    private final CardTransferService cardTransferService;
    private final CardTransferMapper cardTransferMapper;
    private static final Logger logger = LoggerFactory.getLogger(CardTransferRestController.class);
    @Operation(summary = "Add payment via Credit/Debit card")
    @PostMapping("/card")
    public ResponseEntity<HttpStatus> addCardTransfer(@Valid @RequestBody CardTransferDTO cardTransferDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            logger.error("Wrong REST API JSON parameter");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        cardTransferService.add(cardTransferMapper.DTOToCardTransfer(cardTransferDTO));
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
    @Operation(summary = "Get list of card payments for particular Credit/Debit card number")
    @GetMapping("/card/{cardNumber}")
    public List<CardTransferDTO> getCardTransfersByCardNumber (@PathVariable("cardNumber") Long cardNumber) {
        return cardTransferMapper.CardTransferListToDTO(cardTransferService.getCardTransfersByCardNumber(cardNumber));
    }
}
