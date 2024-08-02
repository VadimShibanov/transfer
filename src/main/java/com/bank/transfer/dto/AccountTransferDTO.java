package com.bank.transfer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * Класс для проведения оплат между банковскими расчетными счетами
 * @see com.bank.transfer.entity.AccountTransfer
 */
@Component
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountTransferDTO {

    @NotNull(message = "Should not be NULL")
    @Min(5)
    @Max(1_000_000_000)
    private Long accountNumber;

    @Digits(integer=20,fraction=2, message = "Incompatible BigDecimal type")
    @DecimalMin(value = "0.01", inclusive = false, message = "Wrong amount")
    private BigDecimal amount;

    @NotBlank(message = "Should not be BLANK")
    private String purpose;

    @Min(1)
    @Max(1_000_000_000)
    private Long accountDetails;
}
