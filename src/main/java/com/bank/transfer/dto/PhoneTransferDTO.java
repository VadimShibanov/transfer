package com.bank.transfer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * Класс для обменна данными при проведении оплат по картам
 * @see com.bank.transfer.entity.PhoneTransfer
 */
@Component
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneTransferDTO {

    @NotNull(message = "Should not be NULL")
    @Min(1_000L)
    @Max(1_000_000_000_000_000L)
    private Long phoneNumber;

    @Digits(integer=20,fraction=2, message = "Incompatible BigDecimal type")
    @DecimalMin(value = "0.01", inclusive = false, message = "Wrong amount")
    private BigDecimal amount;

    @NotBlank(message = "Should not be NULL")
    private String purpose;

    @Min(1)
    @Max(1_000_000_000)
    private Long accountDetails;
}
