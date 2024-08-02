package com.bank.transfer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * Сущность, представляющая платеж с использованием банковской карты
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "card_transfer", schema = "transfer")
public class CardTransfer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Should not be NULL")
    @Min(5)
    @Max(1_000_000_000_000_000_000L)
    @Column(name = "card_number", nullable = false)
    private Long cardNumber;

    @Column(name = "amount", nullable = false, precision = 20, scale = 2)
    @Digits(integer=20,fraction=2, message = "Incompatible BigDecimal type")
    @DecimalMin(value = "0.01", inclusive = false, message = "Wrong amount")
    private BigDecimal amount;

    @Column(name = "purpose", nullable = false)
    @NotBlank(message = "Should not be NULL")
    private String purpose;

    @Column(name = "account_details", nullable = false)
    @Min(1)
    @Max(1_000_000_000)
    private Long accountDetails;
}
