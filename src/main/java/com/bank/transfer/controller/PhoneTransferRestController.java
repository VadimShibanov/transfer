package com.bank.transfer.controller;

import com.bank.transfer.dto.PhoneTransferDTO;
import com.bank.transfer.mapper.PhoneTransferMapper;
import com.bank.transfer.service.PhoneTransferService;
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
 * Контроллер операций по оплате с помощью мобильных устройств
 */
@org.springframework.web.bind.annotation.RestController
@AllArgsConstructor
public class PhoneTransferRestController {
    private final PhoneTransferService phoneTransferService;
    private final PhoneTransferMapper phoneTransferMapper;
    private static final Logger logger = LoggerFactory.getLogger(PhoneTransferRestController.class);
    @Operation(summary = "Add payment via mobile device")
    @PostMapping("/phone")
    public ResponseEntity<HttpStatus> addPhoneTransfer(@Valid @RequestBody PhoneTransferDTO phoneTransferDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            logger.error("Wrong REST API JSON parameter");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        phoneTransferService.add(phoneTransferMapper.DTOToPhoneTransfer(phoneTransferDTO));
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
    @GetMapping("/phone/{phoneNumber}")
    public List<PhoneTransferDTO> getPhoneTransfersByPhoneNumber (@PathVariable("phoneNumber") Long phoneNumber) {
        return phoneTransferMapper.PhoneTransferListToDTO(phoneTransferService.getPhoneTransfersByPhoneNumber(phoneNumber));
    }
}
