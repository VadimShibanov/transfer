package com.bank.transfer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * Сущность, представляющая платеж с использованием банковского счета
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "account_transfer", schema = "transfer")
public class AccountTransfer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Should not be NULL")
    @Min(5)
    @Max(1_000_000_000)
    @Column(name = "account_number", nullable = false)
    private Long accountNumber;

    @Column(name = "amount", nullable = false, precision = 20, scale = 2)
    @Digits(integer=20,fraction=2, message = "Incompatible BigDecimal type")
    @DecimalMin(value = "0.01", inclusive = false, message = "Wrong amount")
    private BigDecimal amount;

    @NotBlank(message = "Should not be BLANK")
    @Column(name = "purpose", nullable = false)
    private String purpose;

    @Min(1)
    @Max(1_000_000_000)
    @Column(name = "account_details", nullable = false)
    private Long accountDetails;
}
