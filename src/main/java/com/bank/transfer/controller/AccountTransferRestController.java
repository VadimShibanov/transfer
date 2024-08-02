package com.bank.transfer.controller;

import com.bank.transfer.dto.AccountTransferDTO;
import com.bank.transfer.mapper.AccountTransferMapper;
import com.bank.transfer.service.AccountTransferService;
import org.junit.jupiter.api.Test;
import util.ValidUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер трансферных операций между банковскими счетами, реализующий REST API
 */

@org.springframework.web.bind.annotation.RestController
@AllArgsConstructor
@Log4j2
public class AccountTransferRestController {

    private final AccountTransferService accountTransferService;
    private final AccountTransferMapper accountTransferMapper;
    @Operation(summary="Add payment via bank account")
    @PostMapping("/account")
    public ResponseEntity<Object> addAccountTransfer(@Valid @RequestBody AccountTransferDTO accountTransferDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.error("Wrong REST API JSON parameter");
            return new ResponseEntity<>(ValidUtil.getErrorsForJson(bindingResult),HttpStatus.NOT_ACCEPTABLE);
        }

//        if (accountTransferService.checkIfExistsByAccountNumber(accountTransferDTO.getAccountNumber())) {
//            log.error("Attempt to add transfer record with existing account number");
//            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
//        }

        accountTransferService.add(accountTransferMapper.DTOToAccountTransfer(accountTransferDTO));
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
    @Operation(summary="Get list of account transfers for account with this account number")
    @GetMapping("/account/{accountNumber}")
    public List<AccountTransferDTO> getAccountTransfersByAccountNumber (@PathVariable("accountNumber") Long accountNumber) {
        return accountTransferMapper.AccountTransferListToDTO(accountTransferService.getAccountTransfersByAccountNumber(accountNumber));
    }
    @Operation(summary="Delete all account transfers for this account number")
    @DeleteMapping("/account/{accountNumber}")
    public ResponseEntity<Object> deleteAccountTransfer(@PathVariable("accountNumber") Long accountNumber) {
        if (!accountTransferService.checkIfExistsByAccountNumber(accountNumber)) {
            log.error("Attempt to delete not existing transfer record");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    accountTransferService.deleteAllWithThisAccountNumber(accountNumber);
    return ResponseEntity.ok(HttpStatus.FOUND);
    }
}